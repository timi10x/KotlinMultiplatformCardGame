package me.aliyuolalekan.common.about

import me.aliyuolalekan.common.GlobalResources
import me.aliyuolalekan.common.Strings.ABOUT_GAME_HEADER
import me.aliyuolalekan.common.Strings.FIRE_POWER_14
import me.aliyuolalekan.common.Strings.FIRE_POWER_2
import me.aliyuolalekan.common.Strings.FIRE_POWER_5
import me.aliyuolalekan.common.Strings.FIRE_POWER_8
import me.aliyuolalekan.common.Strings.GAME_DESCRIPTION
import me.aliyuolalekan.common.Strings.GAME_RULES
import me.aliyuolalekan.common.Strings.GENERAL_MARKET_TITLE
import me.aliyuolalekan.common.Strings.HOLD_ON_TITLE
import me.aliyuolalekan.common.Strings.KING_CARD
import me.aliyuolalekan.common.Strings.KING_DESCRIPTION_TITLE
import me.aliyuolalekan.common.Strings.PICK_THREE_TITLE
import me.aliyuolalekan.common.Strings.PICK_TWO_TITLE
import me.aliyuolalekan.common.ingame.data.CardImages

class AboutGameDataRepository {
    fun getAboutGameData(): List<AboutGameData> = aboutGameList
}

private val aboutGameList = listOf(
    AboutGameData(
        ABOUT_GAME_HEADER,
        imagePath = null
    ),
    AboutGameData(
        "Game Description",
        GAME_DESCRIPTION,
        GlobalResources.gamePlayImage
    ),
    AboutGameData(
        "Game Rules",
        GAME_RULES,
        null
    ),
    AboutGameData(
        PICK_TWO_TITLE,
        FIRE_POWER_2,
        null
    ),
    AboutGameData(
        PICK_THREE_TITLE,
        FIRE_POWER_5,
        null
    ),
    AboutGameData(
        HOLD_ON_TITLE,
        FIRE_POWER_8,
        null
    ),
    AboutGameData(
        GENERAL_MARKET_TITLE,
        FIRE_POWER_14,
        null
    ),
    AboutGameData(
        KING_DESCRIPTION_TITLE,
        KING_CARD,
        CardImages.kingCard
    )
)