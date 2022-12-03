package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import me.aliyuolalekan.common.domain.model.Card
import me.aliyuolalekan.common.getImagePainter
import me.aliyuolalekan.common.ui.standardButtonPadding

@Composable
fun PlayCard(
    card: Card,
    width: Dp,
    height: Dp,
    elevation: Dp,
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
    onCardSelected: (Card) -> Unit = {}
) {
    Card(
        modifier = modifier.width(width).height(height)
            .padding(end = standardButtonPadding)
            .clickable(enabled = clickable) { onCardSelected(card) },
        elevation = elevation
    ) {
        Image(
            painter = getImagePainter(card.face),
            contentDescription = card.face,
            modifier = Modifier.width(width).height(height)
        )
    }
}