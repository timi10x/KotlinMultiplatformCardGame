package me.aliyuolalekan.common.domain.model

import me.aliyuolalekan.common.data.Queue
import me.aliyuolalekan.common.data.Stack
import me.aliyuolalekan.common.data.queueOf
import me.aliyuolalekan.common.data.stackOf
import me.aliyuolalekan.common.domain.Player
import kotlin.random.Random

data class GameState(
    val players: Queue<Player> = queueOf(),
    val currentPlayer: Player? = null,
    val marketAreaCards: Stack<Card> = stackOf(),
    val playAreaCards: Stack<Card> = stackOf(),
    val invalidCardPlayed: Card? = null,
    val isTurnOver: Boolean = false,
    val strikeInvoked: Strike? = null,
    val expectedKingCard: Card? = null,
    val isGameOver: Boolean = false,
    val gameOverMessage: String? = null,
    val kingCardSender: String? = null,
    val isProcessingTurn: Boolean = false,
    val gameStarted: Boolean = false
) {
    val opponents: List<Player> = players.asList().filter { !it.isSelf }

    val self: Player? = players.asList().firstOrNull { it.isSelf }

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}