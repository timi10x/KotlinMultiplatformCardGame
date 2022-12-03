package me.aliyuolalekan.common.domain.model

private const val DEFAULT_ID = 1
private const val DEFAULT_START_COUNT = 5
private const val DEFAULT_PLAYER_COUNT_IN_MULTIPLAYER = 3

data class GameSettings(
    val id: Int,
    val startCardCount: Int,
    var isSinglePlayer: Boolean,
    val isSoundEnabled: Boolean
) {
    companion object {
        val DEFAULT = GameSettings(
            DEFAULT_ID,
            DEFAULT_START_COUNT,
            isSinglePlayer = true,
            isSoundEnabled = true
        )
    }
}

fun GameSettings?.orDefault() = this ?: GameSettings.DEFAULT