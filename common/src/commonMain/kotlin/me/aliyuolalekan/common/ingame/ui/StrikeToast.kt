package me.aliyuolalekan.common.ingame.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.model.Strike

// TODO Refactor this to make Strike look more beautiful
@Composable
fun StrikeToast(repository: IGameRepository) {
    val gameState by repository.gameStateFlow.collectAsState()
    if (gameState.strikeInvoked != null) {
        ToastMessage(
            message = getStrikeMessage(
                gameState.kingCardSender.orEmpty(),
                gameState.currentPlayer?.name.orEmpty(),
                gameState.strikeInvoked
            ),
            gameState.strikeInvoked != null,
            ToastDuration.LONG
        ) {}
    }
}

@Composable
fun Counter() {
    val count by remember { mutableStateOf(0) }
    Button(
        onClick = { count + 1 }
    ) {
        Text("Count: $count")
    }
}

private fun getStrikeMessage(kingSender: String?, playerName: String, strike: Strike?): String {
    val sender = kingSender ?: playerName
    return strike?.let { "$sender fired ${it.strikeName}" }.orEmpty()
}

