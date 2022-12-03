package me.aliyuolalekan.common.ui

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun appColors() = colors.copy(
    primary = primaryColor,
    primaryVariant = Color.White,
    onPrimary = onPrimary,
    secondary = textColorPrimary,
    secondaryVariant = accentColor,
    onSecondary = onSecondary
)

@Composable
fun WhotPlusTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = appColors(),
        typography = typography,
        shapes = Shapes,
        content = content
    )
}