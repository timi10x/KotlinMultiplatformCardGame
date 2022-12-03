package me.aliyuolalekan.common.data

import app.cash.turbine.test
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runBlockingTest
import me.aliyuolalekan.common.domain.Player
import me.aliyuolalekan.common.domain.model.CardShape
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.sound.IGameSoundPlayer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class LocalGameRepositoryTest {

    private val soundPlayer: IGameSoundPlayer = mockk()
    private val gameRepository = LocalGameRepository(soundPlayer)

    @BeforeEach
    fun setup() {
        every { soundPlayer.play(any()) } just runs
        gameRepository.initialize(mockPlayers(), mockCards())
    }

    @Test
    fun `when repository is initialized without issue - current player is set`() = runBlockingTest {
        val expectedCards = arrayListOf(
            testCard(CardShape.Star, 14),
            testCard(CardShape.Star, 8),
            testCard(CardShape.Star, 5),
            testCard(CardShape.Star, 2),
            testCard(CardShape.Star, 1)
        )
        gameRepository.gameStateFlow
            .test {
                assertEquals(awaitItem().currentPlayer, Player(1L, "name", "photo", expectedCards, true))
            }
    }

    @Test
    fun `when player plays a valid card - card on play area is updated`() = runBlockingTest {
        val playerCard = testCard(CardShape.Triangle, 5)
        gameRepository.addToPlayAreaCard(playerCard)
        gameRepository.gameStateFlow.map { it.playAreaCards.peek() }.test {
            assertEquals(awaitItem(), playerCard)
        }
    }

    @Test
    fun `when player plays an invalid card - card on play area does not change`() = runBlockingTest {
        val playerCard = testCard(CardShape.Cross, 5)
        gameRepository.addToPlayAreaCard(playerCard)
        gameRepository.gameStateFlow.map { it.playAreaCards.peek() }.test {
            assertNotEquals(awaitItem(), playerCard)
        }
    }

    @Test
    fun `when repository is initialized without issue - market is loaded with cards`() = runBlockingTest {
        gameRepository.gameStateFlow.map { it.marketAreaCards }.test {
            assertTrue(
                awaitItem().asList()
                    .containsAll(
                        listOf(
                            testCard(CardShape.Triangle, 14),
                            testCard(CardShape.Triangle, 5),
                            testCard(CardShape.Triangle, 8),
                            testCard(CardShape.Circle, 1),
                            testCard(CardShape.Circle, 2),
                            testCard(CardShape.Circle, 3),
                            testCard(CardShape.Circle, 5),
                            testCard(CardShape.Circle, 8),
                            testCard(CardShape.Circle, 14),
                            testCard(CardShape.Cross, 1),
                            testCard(CardShape.Cross, 2),
                            testCard(CardShape.Cross, 3),
                            testCard(CardShape.Cross, 5),
                            testCard(CardShape.Cross, 8),
                            testCard(CardShape.Cross, 14),
                            testCard(CardShape.Triangle, 1),
                            testCard(CardShape.Triangle, 2)
                        )
                    )
            )
        }
    }

    @Test
    fun `when repository#addToPlayerCard is invoked - player card is updated from market`() = runBlockingTest {
        verifyMarketSize()
        gameRepository.gameStateFlow.map { it.players }
            .map {
                it.asList().filter { it.id == 1L }
                    .map { it.cards }
            }
            .test {
                assertTrue(awaitItem()[0].size == 5)
            }

        gameRepository.pickCardFromMarketAndAddToPlayerCards()
        gameRepository.gameStateFlow.map { it.players }
            .map {
                it.asList().filter { it.id == 1L }
                    .map { it.cards }
            }
            .test {
                assertTrue(awaitItem()[0].size == 6)
            }
        assertMarketSizeDown()
    }

    @Test
    fun `when repository is initialized without issues - play area has card`() = runBlockingTest {
        gameRepository.gameStateFlow.test {
            assertEquals(awaitItem().playAreaCards.peek(), testCard(CardShape.Triangle, 3))
        }
    }

    @Test
    fun `when repository is initialized without issues - opponents can be observed`() = runBlockingTest {
        gameRepository.gameStateFlow.test {
            val result = awaitItem()
            assertTrue(result.opponents.size == 1)
            assertTrue(result.opponents[0].id == 2L)
        }
    }

    @Test
    fun `when play sound is invoked - market sound is played`() {
        gameRepository.playSound(GameSound.PICK_FROM_MARKET)
        verify { soundPlayer.play(GameSound.PICK_FROM_MARKET) }
    }

    private fun verifyMarketSize() = runBlockingTest {
        gameRepository.gameStateFlow
            .test {
                assertTrue(awaitItem().marketAreaCards.size == 17)
            }
    }

    private fun assertMarketSizeDown() = runBlockingTest {
        gameRepository.gameStateFlow.test {
            assertTrue(awaitItem().marketAreaCards.size == 16)
        }
    }
}

private fun mockPlayers() =
    listOf(
        Player(
            1L, "name", "photo", arrayListOf(), true
        ),
        Player(2L, "name", "photo", arrayListOf(), false)
    )

private fun mockCards() = stackOf(
    testCard(CardShape.Circle, 1),
    testCard(CardShape.Circle, 2),
    testCard(CardShape.Circle, 3),
    testCard(CardShape.Circle, 5),
    testCard(CardShape.Circle, 8),
    testCard(CardShape.Circle, 14),
    testCard(CardShape.Cross, 1),
    testCard(CardShape.Cross, 2),
    testCard(CardShape.Cross, 3),
    testCard(CardShape.Cross, 5),
    testCard(CardShape.Cross, 8),
    testCard(CardShape.Cross, 14),
    testCard(CardShape.Triangle, 1),
    testCard(CardShape.Triangle, 2),
    testCard(CardShape.Triangle, 3),
    testCard(CardShape.Triangle, 5),
    testCard(CardShape.Triangle, 8),
    testCard(CardShape.Triangle, 14),
    testCard(CardShape.Square, 1),
    testCard(CardShape.Square, 2),
    testCard(CardShape.Square, 3),
    testCard(CardShape.Square, 5),
    testCard(CardShape.Square, 14),
    testCard(CardShape.Star, 1),
    testCard(CardShape.Star, 2),
    testCard(CardShape.Star, 5),
    testCard(CardShape.Star, 8),
    testCard(CardShape.Star, 14)
)
