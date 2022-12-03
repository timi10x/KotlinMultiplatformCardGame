package me.aliyuolalekan.common.data.db.mapper

import me.aliyuolalekan.GameSettingsTable
import me.aliyuolalekan.common.domain.model.GameSettings

fun GameSettingsTable.toGameSettingsModel() =
    GameSettings(
        id.toInt(),
        startCardCount.toInt(),
        isSinglePlayer == 1L,
        isSoundEnabled == 1L
    )

fun Iterable<GameSettingsTable>.toDomain() = this.map { it.toGameSettingsModel() }

fun GameSettings.toGameSettingsTableDbData() =
    GameSettingsTable(
        id.toLong(),
        startCardCount.toLong(),
        isSinglePlayer = if (isSinglePlayer) 1L else 0L,
        isSoundEnabled = if (isSoundEnabled) 1L else 0L
    )
