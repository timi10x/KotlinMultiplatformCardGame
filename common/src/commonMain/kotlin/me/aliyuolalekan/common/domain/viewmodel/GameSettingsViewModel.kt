package me.aliyuolalekan.common.domain.viewmodel

import kotlinx.coroutines.flow.Flow
import me.aliyuolalekan.common.domain.IGameSettingsRepository
import me.aliyuolalekan.common.domain.model.GameSettings

class GameSettingsViewModel(private val repository: IGameSettingsRepository) {

    val getGameSettings: Flow<GameSettings?>
        get() = repository.getGameSettings()

    fun setGameSettings(gameSettings: GameSettings) {
        repository.updateGameSettings(gameSettings)
    }
}