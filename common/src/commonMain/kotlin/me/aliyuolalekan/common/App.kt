package me.aliyuolalekan.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import me.aliyuolalekan.common.splashscreen.windowsManager
import me.aliyuolalekan.common.ui.WhotPlusTheme

@Composable
fun App() {
    WhotPlusTheme {
        val backClickListener by backClickEvent.collectAsState()
        if (backClickListener != null) {
            windowsManager.popBackStack()
            onBackClicked(windowsManager.size)
        }
        val currentWindows by windowsManager.appScreensFlow.collectAsState()
        currentWindows.screens.peek()?.let { it() }
    }
}



