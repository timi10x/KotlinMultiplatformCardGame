package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.model.Strike
import me.aliyuolalekan.common.servicelocator.ServiceLocator
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.ui.playAreaCardElevation
import me.aliyuolalekan.common.ui.playAreaCardHeight
import me.aliyuolalekan.common.ui.playAreaCardWidth
import me.aliyuolalekan.common.ui.playerCardElevation
import me.aliyuolalekan.common.ui.playerCardHeight
import me.aliyuolalekan.common.ui.playerCardWidth
import me.aliyuolalekan.common.ui.standardContentMargin

@Composable
fun PlayArea(repository: IGameRepository) {
    val gameState by repository.gameStateFlow.collectAsState()
    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight(), contentAlignment = Alignment.Center) {
        gameState.playAreaCards.peek()?.let { card ->
            PlayCard(card, playAreaCardWidth, playAreaCardHeight, playAreaCardElevation, clickable = false)
        }
    }
}

@Composable
fun PlayerCards(repository: IGameRepository) {
    val gameState by repository.gameStateFlow.collectAsState()
    val isClickable = gameState.currentPlayer!!.isSelf
            && !gameState.isGameOver
    val currentPlayerCards = gameState.self?.cards?.reversed() ?: listOf()
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = standardContentMargin)
    ) {
        items(currentPlayerCards) { card ->
            PlayCard(card, playerCardWidth, playerCardHeight, playerCardElevation, clickable = isClickable) {
                gameState.currentPlayer?.let {
                    if (it.isSelf) {
                        repository.addToPlayAreaCard(card)
                    } else {
                        // ShowToast(Not your turn)
                    }
                }
            }
        }
    }
}