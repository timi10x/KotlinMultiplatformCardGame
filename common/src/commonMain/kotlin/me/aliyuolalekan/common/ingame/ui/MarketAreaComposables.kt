package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.model.Card
import me.aliyuolalekan.common.ingame.data.CardImages
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.ui.marketCardElevation
import me.aliyuolalekan.common.ui.marketCardHeight
import me.aliyuolalekan.common.ui.marketCardWidth

@Composable
fun MarketArea(repository: IGameRepository) {
    val card = Card.DEFAULT.copy(back = CardImages.cardBack)
    card.flip()
    PlayCard(
        card,
        marketCardWidth,
        marketCardHeight,
        marketCardElevation,
        modifier = Modifier.padding(2.dp),
        clickable = true
    ) {
        repository.playSound(GameSound.PICK_FROM_MARKET)
        repository.pickCardFromMarketAndAddToPlayerCards()
    }
}