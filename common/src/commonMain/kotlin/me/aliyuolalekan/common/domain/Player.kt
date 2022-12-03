package me.aliyuolalekan.common.domain

import me.aliyuolalekan.common.domain.model.Card

data class Player(
    val id: Long,
    val name: String,
    val photo: String = "",
    val cards: ArrayList<Card> = arrayListOf(),
    val isSelf: Boolean = false,
    val isAI: Boolean = false
) {
    fun addCard(card: Card) = cards.add(card)
    fun playCard(card: Card) = cards.remove(card)
}