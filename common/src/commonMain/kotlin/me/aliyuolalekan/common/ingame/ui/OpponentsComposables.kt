package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.Player
import me.aliyuolalekan.common.getImagePainter
import me.aliyuolalekan.common.ui.normalAvatarSize
import me.aliyuolalekan.common.ui.opponentContentHeight
import me.aliyuolalekan.common.ui.opponentContentWidth
import me.aliyuolalekan.common.ui.smallPadding
import me.aliyuolalekan.common.ui.standardAvatarSize
import me.aliyuolalekan.common.ui.standardButtonPadding

@Composable
fun Opponents(repository: IGameRepository, modifier: Modifier = Modifier) {
    val gameState by repository.gameStateFlow.collectAsState()
    LazyRow(horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth().wrapContentHeight()) {
        items(gameState.opponents) { opponent ->
            OpponentAvatar(opponent)
        }
    }
}

@Composable
fun OpponentAvatar(player: Player) {
    Box(
        modifier = Modifier.wrapContentWidth().wrapContentHeight()
    ) {
        Card(modifier = Modifier.width(opponentContentWidth).wrapContentHeight()) {
            OpponentContent(player)
        }
    }
}

@Composable
fun OpponentContent(player: Player) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(standardAvatarSize).wrapContentHeight().padding(smallPadding)
    ) {
        Box(
            modifier = Modifier.width(normalAvatarSize).height(normalAvatarSize).clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = getImagePainter(player.photo),
                contentDescription = player.name,
                modifier = Modifier.width(normalAvatarSize).height(normalAvatarSize)
            )
        }
        Text(
            getOpponentLabel(player),
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

private fun getOpponentLabel(player: Player): String {
    val suffix = if (player.cards.size <= 1) {
        "Card"
    } else {
        "Cards"
    }
    return "${player.cards.size} $suffix"
}
