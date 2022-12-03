package me.aliyuolalekan.common.data.db.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneNotNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import me.aliyuolalekan.WhotPlusDatabase
import me.aliyuolalekan.common.data.db.mapper.toGameSettingsModel
import me.aliyuolalekan.common.domain.model.GameSettings

class GameSettingsDao(private val db: WhotPlusDatabase) : IGameSettingsDao {
    override fun updateGameSettings(gameSettings: GameSettings) {
        db.gameSettingsQueries.insertOrReplace(
            gameSettings.id.toLong(),
            gameSettings.startCardCount.toLong(),
            if (gameSettings.isSinglePlayer) 1L else 0L,
            if (gameSettings.isSoundEnabled) 1L else 0L
        )
    }

    override fun getGameSettings(): Flow<GameSettings> {
        return db.gameSettingsQueries.selectAll().asFlow()
            .mapToOneNotNull().filterNotNull().map { it.toGameSettingsModel() }
    }
}