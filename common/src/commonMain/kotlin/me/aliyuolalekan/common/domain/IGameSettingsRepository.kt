package me.aliyuolalekan.common.domain

import kotlinx.coroutines.flow.Flow
import me.aliyuolalekan.common.domain.model.GameSettings

interface IGameSettingsRepository {
    fun updateGameSettings(gameSettings: GameSettings)
    fun getGameSettings(): Flow<GameSettings?>
}