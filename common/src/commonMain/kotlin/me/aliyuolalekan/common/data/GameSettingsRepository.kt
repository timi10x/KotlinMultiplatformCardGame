package me.aliyuolalekan.common.data

import me.aliyuolalekan.common.data.db.dao.IGameSettingsDao
import me.aliyuolalekan.common.domain.IGameSettingsRepository
import me.aliyuolalekan.common.domain.model.GameSettings

class GameSettingsRepository(private val dao: IGameSettingsDao) : IGameSettingsRepository {
    override fun updateGameSettings(gameSettings: GameSettings) = dao.updateGameSettings(gameSettings)
    override fun getGameSettings() = dao.getGameSettings()
}