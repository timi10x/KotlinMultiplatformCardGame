package me.aliyuolalekan.common.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import me.aliyuolalekan.common.data.db.dao.IGameSettingsDao
import me.aliyuolalekan.common.domain.model.GameSettings
import org.junit.jupiter.api.Test

internal class GameSettingsRepositoryTest {

    private val dao: IGameSettingsDao = mockk(relaxed = true)
    private val repository = GameSettingsRepository(dao)

    @Test
    fun `when repository#updateGameSettings is invoked - dao is updated`() {
        val gameSettings = GameSettings.DEFAULT
        repository.updateGameSettings(gameSettings)
        verify { dao.updateGameSettings(gameSettings) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when repository#getGameSettings is invoked - cached game settings is returned`() = runBlockingTest {
        val gameSettings = flowOf(GameSettings.DEFAULT)
        coEvery { dao.getGameSettings() }.coAnswers { gameSettings }
        repository.updateGameSettings(GameSettings.DEFAULT)
        coVerify { dao.updateGameSettings(GameSettings.DEFAULT) }
    }
}