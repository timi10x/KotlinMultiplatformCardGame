package me.aliyuolalekan.common.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CardTest {

    private val cardFront = "testFront"
    private val cardBack = "cardBack"

    private val testCard = Card.DEFAULT.copy(front = cardFront, back = cardBack)

    @Test
    fun `when card is flipped - card back is up`() {
        assertEquals(cardFront, testCard.face)
        testCard.flip()
        assertEquals(cardBack, testCard.face)
    }

    @Test
    fun `when card$strike is invoked - card strike is observed`() {
        assertEquals(Strike.I_NEED, testCard.strike)
    }
}