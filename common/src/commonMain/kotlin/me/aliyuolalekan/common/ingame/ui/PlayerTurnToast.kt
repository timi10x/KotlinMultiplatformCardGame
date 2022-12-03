package me.aliyuolalekan.common.ingame.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import me.aliyuolalekan.common.domain.IGameRepository

private const val YOUR_TURN_MESSAGE = "It's your turn"

@Composable
fun PlayerTurn(repository: IGameRepository) {
    val gameState by repository.gameStateFlow.collectAsState()
    ToastMessage(
        message = if (gameState.currentPlayer!!.isSelf) YOUR_TURN_MESSAGE else "${gameState.currentPlayer?.name}'s turn",
        gameState.isTurnOver,
        ToastDuration.LONG
    ) {
        repository.opponentTurn()
    }
}