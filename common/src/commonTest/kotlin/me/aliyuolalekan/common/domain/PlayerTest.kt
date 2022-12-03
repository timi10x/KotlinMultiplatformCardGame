package me.aliyuolalekan.common.domain

import me.aliyuolalekan.common.data.testCard
import me.aliyuolalekan.common.domain.model.CardShape
import me.aliyuolalekan.common.ingame.ui.getTestPlayers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class PlayerTest {
    private val testPlayer = getTestPlayers()[0]
    private val testCard = testCard(CardShape.Cross, 14)

    @Test
    fun `when player$addCard is invoked - card is added to player cards`() {
        assertTrue(testPlayer.cards.isEmpty())
        testPlayer.addCard(testCard)
        assertEquals(1, testPlayer.cards.size)
        assertEquals(testCard, testPlayer.cards[0])
    }

    @Test
    fun `when player#playCard is invoked - card is removed from player cards`() {
        testPlayer.addCard(testCard)
        assertEquals(1, testPlayer.cards.size)
        testPlayer.playCard(testCard)
        assertTrue(testPlayer.cards.isEmpty())
    }
}