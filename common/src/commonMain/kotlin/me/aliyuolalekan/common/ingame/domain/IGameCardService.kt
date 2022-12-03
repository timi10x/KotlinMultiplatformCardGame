package me.aliyuolalekan.common.ingame.domain

import me.aliyuolalekan.common.domain.model.Card

interface IGameCardService {
    fun getAllCardsShuffled(): List<Card>
    fun getAllCards(): List<Card>
}