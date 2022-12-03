package me.aliyuolalekan.common.data.db.dao

import kotlinx.coroutines.flow.Flow
import me.aliyuolalekan.common.domain.model.GameSettings

interface IGameSettingsDao {
    fun updateGameSettings(gameSettings: GameSettings)
    fun getGameSettings(): Flow<GameSettings?>
}