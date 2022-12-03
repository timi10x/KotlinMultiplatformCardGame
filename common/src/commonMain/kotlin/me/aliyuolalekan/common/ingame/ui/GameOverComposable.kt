package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.aliyuolalekan.common.Strings
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.ingame.data.cardService
import me.aliyuolalekan.common.servicelocator.ServiceLocator
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.splashscreen.windowsManager
import me.aliyuolalekan.common.ui.VerticalSpace
import me.aliyuolalekan.common.ui.standardContentMargin
import me.aliyuolalekan.common.ui.standardGameOverScreenSize
import me.aliyuolalekan.common.ui.transparent
import me.aliyuolalekan.common.ui.typography

val gameSoundPlayer = ServiceLocator.soundPlayer

@Composable
fun GameOverScreen(repository: IGameRepository) {
    val gameState by repository.gameStateFlow.collectAsState()
    if (gameState.isGameOver) {
        gameSoundPlayer.play(GameSound.GAME_OVER)
        Box(modifier = Modifier.fillMaxSize().background(color = transparent)) {
            Box(
                modifier = Modifier.background(Color.White)
                    .width(standardGameOverScreenSize)
                    .height(standardGameOverScreenSize)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Game Over, ${gameState.gameOverMessage}",
                        style = typography.h6, modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    VerticalSpace(standardContentMargin)
                    Button(onClick = {
                        repository.initialize(getTestPlayers(), cardService.getAllCardsShuffled().toStack())
                    }) {
                        Text(Strings.NEW_GAME)
                    }
                    VerticalSpace(standardContentMargin)
                    Button(onClick = {
                        windowsManager.popBackStack()
                    }) {
                        Text(Strings.MAIN_MENU)
                    }
                }
            }
        }
    }
}