package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.model.isKingStrike
import me.aliyuolalekan.common.domain.model.shapeName
import me.aliyuolalekan.common.ui.standardContentMargin

@Composable
fun KingRequest(repository: IGameRepository) {
    val gameState by repository.gameStateFlow.collectAsState(null)
    Box(
        modifier = Modifier.background(Color.White).padding(start = standardContentMargin).wrapContentWidth()
            .wrapContentHeight(), contentAlignment = Alignment.CenterEnd
    ) {
        val strikeInvoked = gameState?.strikeInvoked
        if (strikeInvoked != null && strikeInvoked.isKingStrike()) {
            Column(modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(
                    text = if (gameState?.expectedKingCard == null)
                        "${gameState?.currentPlayer?.name.orEmpty()} Select your choice card please"
                    else "King expects ${gameState?.expectedKingCard?.shapeName()}"
                )
            }
        }
    }
}