package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.ingame.data.cardService
import me.aliyuolalekan.common.servicelocator.ServiceLocator
import me.aliyuolalekan.common.ui.VerticalSpace
import me.aliyuolalekan.common.ui.standardContentMargin

private val repository: IGameRepository = ServiceLocator.localGameRepository

@Composable
fun InGameScreen() {
    repository.initialize(getTestPlayers(), cardService.getAllCardsShuffled().toStack())
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.TopCenter).padding(top = standardContentMargin)) {
            Opponents(repository)
        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
                PlayArea(repository)
                VerticalSpace(standardContentMargin)
                KingRequest(repository)
                VerticalSpace(standardContentMargin)
                ToastBox(repository)
            }
        }

        Box(modifier = Modifier.align(Alignment.CenterStart).padding(start = standardContentMargin)) {
            MarketArea(repository)
        }

        Box(
            modifier = Modifier.align(Alignment.CenterEnd)
                .padding(start = standardContentMargin, end = standardContentMargin)
        ) {
            PlayerTurn(repository)
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = standardContentMargin)) {
            PlayerCards(repository)
        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            GameOverScreen(repository)
        }
    }
}