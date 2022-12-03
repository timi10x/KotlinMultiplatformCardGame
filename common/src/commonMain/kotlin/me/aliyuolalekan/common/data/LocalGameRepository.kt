package me.aliyuolalekan.common.data

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.aliyuolalekan.common.Strings
import me.aliyuolalekan.common.ai.AIBrain
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.Player
import me.aliyuolalekan.common.domain.model.Card
import me.aliyuolalekan.common.domain.model.CardShape
import me.aliyuolalekan.common.domain.model.GameState
import me.aliyuolalekan.common.domain.model.Strike
import me.aliyuolalekan.common.domain.model.isKingStrike
import me.aliyuolalekan.common.domain.model.toCardNumber
import me.aliyuolalekan.common.ingame.ui.toStack
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.sound.IGameSoundPlayer

class LocalGameRepository(private val soundPlayer: IGameSoundPlayer) : IGameRepository {
    private val gameStateMutableStateFlow: MutableStateFlow<GameState> = MutableStateFlow(GameState())

    override val gameStateFlow: StateFlow<GameState>
        get() = gameStateMutableStateFlow

    override fun addToPlayAreaCard(card: Card) {
        val currentGameState = gameStateMutableStateFlow.value
        if (isCardValidForPlay(card)) {
            val currentPlayer = currentGameState.currentPlayer ?: return

            playSound(card.strike)

            if (!card.isKing && currentGameState.strikeInvoked != null && currentGameState.strikeInvoked.isKingStrike() && currentGameState.expectedKingCard == null) {
                gameStateMutableStateFlow.value =
                    currentGameState.copy(expectedKingCard = card, kingCardSender = currentPlayer.name)
                giveUpTurn()
                return
            }

            val playAreaCardsStack = currentGameState.playAreaCards
            playAreaCardsStack.push(card)
            currentPlayer.playCard(card)

            val isGameOver = currentPlayer.cards.isEmpty()

            gameStateMutableStateFlow.value = gameStateFlow.value.copy(
                playAreaCards = playAreaCardsStack,
                currentPlayer = currentPlayer,
                isGameOver = isGameOver,
                gameOverMessage = if (isGameOver) getGameOverMessage(currentPlayer) else null
            )
            if (isGameOver) {
                return
            }

            if (card.isKing) {
                gameStateMutableStateFlow.value = currentGameState.copy(
                    kingCardSender = null,
                    expectedKingCard = null,
                    strikeInvoked = null
                )
                executeKingCard(card.strike)
                return
            }

            if (card.strike == Strike.NONE) {
                gameStateMutableStateFlow.value = currentGameState.copy(
                    kingCardSender = null,
                    expectedKingCard = null,
                    strikeInvoked = null
                )
                giveUpTurn()
            } else {
                gameStateMutableStateFlow.value = currentGameState.copy(
                    kingCardSender = null,
                    expectedKingCard = null,
                    strikeInvoked = null
                )
                resolveStrikeFired(card.strike, currentPlayer)
            }
        } else {
            gameStateMutableStateFlow.value = currentGameState.copy(invalidCardPlayed = card)
        }
    }

    private fun getGameOverMessage(currentPlayer: Player) =
        if (currentPlayer.isSelf) Strings.WON_MESSAGE else "${currentPlayer.name} has won!"

    private fun executeKingCard(strike: Strike) {
        val gameState = gameStateMutableStateFlow.value
        gameStateMutableStateFlow.value = gameState.copy(strikeInvoked = strike, isProcessingTurn = true)
    }

    private fun resolveStrikeFired(strike: Strike, currentPlayer: Player) {
        when (strike) {
            Strike.PICK_TWO -> invokePickTwo(currentPlayer)
            Strike.PICK_THREE -> invokePickThree(currentPlayer)
            Strike.GENERAL_MARKET -> invokeGeneralMarket(currentPlayer)
            Strike.HOLD_ON -> holdOn()
            Strike.I_NEED -> executeKingCard(strike)
            Strike.NONE -> giveUpTurn()
        }
    }

    private fun giveUpTurn() {
        val gameState = gameStateMutableStateFlow.value
        gameStateMutableStateFlow.value =
            gameState.copy(currentPlayer = gameState.players.nextTo(gameState.currentPlayer), isTurnOver = true)
    }

    override fun opponentTurn() {
        executePlayForAI()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun executePlayForAI() {
        val gameState = gameStateMutableStateFlow.value
        val currentPlayer = requireNotNull(gameState.currentPlayer)

        if (currentPlayer.isAI) {
            if (gameState.strikeInvoked != null &&
                gameState.strikeInvoked.isKingStrike() &&
                gameState.expectedKingCard == null
            ) {
                AIBrain.selectBestCard(currentPlayer.cards) { bestCard ->
                    addToPlayAreaCard(requireNotNull(bestCard) { "Best card cannot be null" })
                }
                return
            }

            if (gameState.expectedKingCard != null) {
                AIBrain.selectBestCard(currentPlayer.cards, true, gameState.expectedKingCard) { card ->
                    playCardForAI(card)
                }
                return
            }

            val card = gameState.playAreaCards.peek()
            AIBrain.selectBestCard(currentPlayer.cards, false, card) { bestCard ->
                playCardForAI(bestCard)
            }
        }
    }

    private fun playCardForAI(card: Card?) {
        if (card == null) {
            soundPlayer.play(GameSound.PICK_FROM_MARKET)
            pickCardFromMarketAndAddToPlayerCards()
        } else {
            addToPlayAreaCard(card)
        }
    }

    private fun invokePickTwo(currentPlayer: Player) {
        val gameState = gameStateMutableStateFlow.value
        val nextPlayer = gameState.players.nextTo(currentPlayer)
        for (count in 0 until Strike.PICK_TWO.power) {
            nextPlayer?.addCard(pickCardFromMarket())
        }
        gameStateMutableStateFlow.value = gameState.copy(strikeInvoked = null)
        executePlayForAI()
    }

    private fun invokePickThree(currentPlayer: Player) {
        val gameState = gameStateMutableStateFlow.value
        val nextPlayer = gameState.players.nextTo(currentPlayer)
        for (count in 0 until Strike.PICK_THREE.power) {
            nextPlayer?.addCard(pickCardFromMarket())
        }
        gameStateMutableStateFlow.value = gameState.copy(strikeInvoked = null)
        executePlayForAI()
    }

    private fun holdOn() {
        gameStateMutableStateFlow.value =
            gameStateMutableStateFlow.value.copy(strikeInvoked = null, isProcessingTurn = true)
    }

    private fun invokeGeneralMarket(currentPlayer: Player) {
        val gameState = gameStateMutableStateFlow.value
        for (player in gameState.players.asList().filter { it != currentPlayer }) {
            player.addCard(pickCardFromMarket())
        }
        gameStateMutableStateFlow.value = gameState.copy(strikeInvoked = null, isProcessingTurn = true)
    }

    override fun pickCardFromMarketAndAddToPlayerCards() {
        val currentGameState = gameStateMutableStateFlow.value
        val card = pickCardFromMarket()
        currentGameState.currentPlayer?.addCard(card)
        gameStateMutableStateFlow.value = currentGameState
        giveUpTurn()
    }

    private fun pickCardFromMarket(): Card {
        val currentGameState = gameStateMutableStateFlow.value
        val currentCards = currentGameState.marketAreaCards

        if (currentCards.size < 5) {
            pullCardsFromPlayAreaAndShuffle()
        }
        return currentCards.pop()!!
    }

    private fun pullCardsFromPlayAreaAndShuffle() {
        val currentGameState = gameStateMutableStateFlow.value
        val playAreaCards = currentGameState.playAreaCards
        val topCard = requireNotNull(playAreaCards.pop())
        val marketCards = currentGameState.marketAreaCards
        playAreaCards.shuffle()
        val size = playAreaCards.size

        repeat(size) {
            val card = requireNotNull(playAreaCards.pop())
            marketCards.addFromBack(card)
        }
        playAreaCards.push(topCard)

        val updatedGameState = currentGameState.copy(
            playAreaCards = playAreaCards,
            marketAreaCards = marketCards
        )

        gameStateMutableStateFlow.value = updatedGameState
    }

    override fun initialize(players: List<Player>, cards: Stack<Card>) {
        val queue = Queue<Player>()

        for (player in players) {
            queue.enqueue(player)
        }

        val player1 = players.first { player -> player.isSelf }

        repeat(5) { player1.addCard(cards.pop()!!) }

        players.filter { player -> !player.isSelf }
            .forEach { player ->
                repeat(5) {
                    player.addCard(cards.pop()!!)
                }
            }
        var playAreaCard: Card? = cards.pop()

        val cardsList = cards.asList().toMutableList()

        while (playAreaCard?.strike != Strike.NONE) {
            cardsList.add(0, playAreaCard!!)
            playAreaCard = cardsList.removeLast()
        }
        val currentGameState = gameStateMutableStateFlow.value

        val updatedGameState = currentGameState.copy(
            players = queue,
            currentPlayer = queue.peek(),
            marketAreaCards = cardsList.toStack(),
            playAreaCards = stackOf(playAreaCard),
            isGameOver = false,
            gameOverMessage = null,
            gameStarted = true
        )

        gameStateMutableStateFlow.value = updatedGameState
    }

    override fun isCardValidForPlay(card: Card): Boolean {
        val currentGameState = gameStateMutableStateFlow.value

        if (card.isKing) {
            return true
        }

        if (
            currentGameState.strikeInvoked != null &&
            currentGameState.strikeInvoked.isKingStrike() &&
            currentGameState.expectedKingCard == null
        ) {
            return true
        }

        if (currentGameState.expectedKingCard != null) {
            return requireNotNull(currentGameState.expectedKingCard).matchKingCard(card)
        }

        val expectedCard = requireNotNull(currentGameState.playAreaCards.peek())
        return card.matches(expectedCard)
    }

    override fun clearWrongCardSelected() {
        gameStateMutableStateFlow.value = gameStateMutableStateFlow.value.copy(invalidCardPlayed = null)
    }

    override fun clear() {
        gameStateMutableStateFlow.value = GameState()
    }

    override fun playSound(gameSound: GameSound) {
        soundPlayer.play(gameSound)
    }

    override fun onPlayResolved() {
        val currentGameState = gameStateMutableStateFlow.value
        gameStateMutableStateFlow.value = currentGameState.copy(isProcessingTurn = false)
        executePlayForAI()
    }

    private fun playSound(strike: Strike) {
        when (strike) {
            Strike.I_NEED -> soundPlayer.play(GameSound.KING_REQUEST)
            Strike.HOLD_ON -> soundPlayer.play(GameSound.HOLD_ON)
            Strike.PICK_TWO -> soundPlayer.play(GameSound.PICK_TWO)
            Strike.PICK_THREE -> soundPlayer.play(GameSound.PICK_THREE)
            Strike.GENERAL_MARKET -> soundPlayer.play(GameSound.GENERAL_MARKET)
            Strike.NONE -> {}
        }
    }
}


fun testCard(shape: CardShape, number: Int, front: String = "", back: String = "") =
    Card.DEFAULT.copy(shape = shape, number = number.toCardNumber(), front = front, back = back)