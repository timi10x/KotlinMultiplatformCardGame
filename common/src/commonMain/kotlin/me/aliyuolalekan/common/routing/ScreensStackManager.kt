package me.aliyuolalekan.common.routing

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.aliyuolalekan.common.data.Stack
import me.aliyuolalekan.common.data.stackOf
import kotlin.random.Random

class ScreensStackManager {

    private val appScreensStateFlow: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState())

    val size: Int
        get() = appScreensFlow.value.screens.size

    val appScreensFlow: StateFlow<ScreenState>
        get() = appScreensStateFlow

    fun pushScreen(screen: @Composable () -> Unit) {
        val currentScreens = appScreensStateFlow.value
        currentScreens.screens.push(screen)
        appScreensStateFlow.value = currentScreens
    }

    fun popBackStack() {
        val currentScreen = appScreensStateFlow.value
        currentScreen.screens.pop()
        appScreensStateFlow.value = currentScreen
    }
}

data class ScreenState(val screens: Stack<@Composable () -> Unit> = stackOf()) {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}