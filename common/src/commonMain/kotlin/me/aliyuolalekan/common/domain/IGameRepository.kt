package me.aliyuolalekan.common.domain

import kotlinx.coroutines.flow.StateFlow
import me.aliyuolalekan.common.data.Stack
import me.aliyuolalekan.common.domain.model.Card
import me.aliyuolalekan.common.domain.model.GameState
import me.aliyuolalekan.common.domain.model.Strike
import me.aliyuolalekan.common.sound.GameSound

interface IGameRepository {
    val gameStateFlow: StateFlow<GameState>
    fun pickCardFromMarketAndAddToPlayerCards()
    fun addToPlayAreaCard(card: Card)
    fun initialize(players: List<Player>, cards: Stack<Card>)
    fun isCardValidForPlay(card: Card): Boolean
    fun clearWrongCardSelected()
    fun opponentTurn()
    fun clear()
    fun onPlayResolved()
    fun playSound(gameSound: GameSound)
}