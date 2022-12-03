package me.aliyuolalekan.common.servicelocator

import me.aliyuolalekan.common.data.GameSettingsRepository
import me.aliyuolalekan.common.data.LocalGameRepository
import me.aliyuolalekan.common.data.db.createWhotPlusDatabase
import me.aliyuolalekan.common.data.db.dao.GameSettingsDao
import me.aliyuolalekan.common.data.db.dao.IGameSettingsDao
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.IGameSettingsRepository
import me.aliyuolalekan.common.domain.viewmodel.GameSettingsViewModel
import me.aliyuolalekan.common.getGameSoundPlayer
import me.aliyuolalekan.common.getLogger
import me.aliyuolalekan.common.ingame.data.GameCardService
import me.aliyuolalekan.common.ingame.domain.IGameCardService
import me.aliyuolalekan.common.logger.Logger
import me.aliyuolalekan.common.routing.ScreensStackManager
import me.aliyuolalekan.common.sound.IGameSoundPlayer
import me.aliyuolalekan.common.splashscreen.SplashScreen

object ServiceLocator {
    private val gameSettingsDao: IGameSettingsDao = GameSettingsDao(createWhotPlusDatabase())
    private val gameSettingsRepository: IGameSettingsRepository = GameSettingsRepository(gameSettingsDao)
    val gameSettingsViewModel: GameSettingsViewModel by lazy { GameSettingsViewModel(gameSettingsRepository) }

    val localGameRepository: IGameRepository  by lazy { LocalGameRepository(soundPlayer) }
    val cardService: IGameCardService by lazy { GameCardService() }

    val logger: Logger by lazy { getLogger() }

    val screensStackManager: ScreensStackManager by lazy { ScreensStackManager().apply { pushScreen { SplashScreen() } } }

    val soundPlayer: IGameSoundPlayer by lazy { getGameSoundPlayer() }
}