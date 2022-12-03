package me.aliyuolalekan.common.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.aliyuolalekan.common.Strings
import me.aliyuolalekan.common.domain.model.GameSettings
import me.aliyuolalekan.common.domain.viewmodel.GameSettingsViewModel
import me.aliyuolalekan.common.homescreen.ui.GameHome
import me.aliyuolalekan.common.ingame.ui.ToastDuration
import me.aliyuolalekan.common.ingame.ui.toLong
import me.aliyuolalekan.common.servicelocator.ServiceLocator
import me.aliyuolalekan.common.splashscreen.components.LoadingComposable
import me.aliyuolalekan.common.ui.darkBrown
import me.aliyuolalekan.common.ui.typography

val windowsManager = ServiceLocator.screensStackManager
private const val gameSettingsDataCannotBeNull = "Game Setting data cannot be null"

@Composable
fun SplashScreen() {
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        LoadingAnimation(modifier = Modifier.align(Alignment.Center))
        //Text(Strings.APP_NAME, style = typography.h6, modifier = Modifier.align(Alignment.Center))
    }
    scope.launch {
        delay(ToastDuration.LONG.toLong())
        windowsManager.pushScreen { GameHome() }
    }
}

@Composable
fun GameHome() {
    val viewModel = ServiceLocator.gameSettingsViewModel
    val gameSettings = viewModel.getGameSettings.collectAsState(null)
    if (gameSettings.value == null) {
        initHomeScreenWithDefaults(viewModel)
    } else {
        initHomeScreenWithData(gameSettings, viewModel)
    }
}

@Composable
private fun initHomeScreenWithData(
    gameSettings: State<GameSettings?>,
    viewModel: GameSettingsViewModel
) {
    GameHome(requireNotNull(gameSettings.value) { gameSettingsDataCannotBeNull }) { settings ->
        viewModel.setGameSettings(settings)
    }
}

@Composable
private fun initHomeScreenWithDefaults(viewModel: GameSettingsViewModel) {
    GameHome(GameSettings.DEFAULT) { gameSettings ->
        viewModel.setGameSettings(gameSettings)
    }
}

@Composable
fun LoadingAnimation(color: Color = darkBrown, radiusEnd: Float = 200f, modifier: Modifier = Modifier) {
    LoadingComposable(color, radiusEnd, modifier)
}