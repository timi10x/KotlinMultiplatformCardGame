package me.aliyuolalekan.common.ai

import me.aliyuolalekan.common.domain.model.Card

object AIBrain {

    fun selectBestCard(cards: List<Card>, onCardSelectedCallback: (Card?) -> Unit) {
        val bestCard = cards.filter { !it.isKing }.groupBy { it.shape }.maxByOrNull { it.value.size }
        onCardSelectedCallback(bestCard?.value?.firstOrNull() ?: cards.firstOrNull())
    }

    fun selectBestCard(
        cards: List<Card>,
        isKingCard: Boolean,
        proposedCard: Card?,
        onCardSelectedCallback: (Card?) -> Unit
    ) {
        val cardInPlay = if (proposedCard != null) {
            proposedCard
        } else {
            onCardSelectedCallback(null)
            return
        }

        if (isKingCard) {
            val matchingCard = cards.firstOrNull { it.matchKingCard(cardInPlay) }
            if (matchingCard != null) {
                onCardSelectedCallback(matchingCard)
            } else {
                onCardSelectedCallback(null)
            }
        } else {
            val matchingNumberCards = arrayListOf<Card>()
            val matchingShapes = arrayListOf<Card>()

            for (card in cards) {
                if (card.number == cardInPlay.number) {
                    matchingNumberCards.add(card)
                }
                if (card.shape == cardInPlay.shape) {
                    matchingShapes.add(card)
                }
            }

            when {
                matchingShapes.isEmpty() && matchingNumberCards.isEmpty() && cards.firstOrNull { it.isKing } != null -> {
                    onCardSelectedCallback(cards.firstOrNull { it.isKing })
                }
                matchingShapes.size < 3 && matchingNumberCards.size < 3 -> {
                    val kingCard = cards.firstOrNull { it.isKing }
                    if (kingCard != null) {
                        onCardSelectedCallback(kingCard)
                    } else {
                        onCardSelectedCallback(matchingShapes.firstOrNull())
                    }
                }
                matchingNumberCards.isEmpty() && matchingShapes.isEmpty() -> {
                    onCardSelectedCallback(null)
                }
                matchingNumberCards.isEmpty() -> {
                    onCardSelectedCallback(matchingShapes.firstOrNull())
                }
                matchingShapes.isEmpty() -> {
                    onCardSelectedCallback(matchingNumberCards.firstOrNull())
                }
                matchingNumberCards.size == matchingShapes.size -> {
                    onCardSelectedCallback(matchingShapes.firstOrNull())
                }
                matchingNumberCards.size > matchingShapes.size -> {
                    onCardSelectedCallback(matchingNumberCards.firstOrNull())
                }
                matchingShapes.size > matchingNumberCards.size -> {
                    onCardSelectedCallback(matchingShapes.firstOrNull())
                }
                else -> {
                    onCardSelectedCallback(null)
                }
            }
        }
    }
}