package me.aliyuolalekan.common.splashscreen.components

import androidx.compose.animation.core.*
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import me.aliyuolalekan.common.ui.darkBrown

@Composable
fun LoadingComposable(
    color: Color = darkBrown,
    radiusEnd: Float = 200f,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition()
    val floatAnim by transition.animateFloat(
        initialValue = 10f,
        targetValue = radiusEnd,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse)
    )
    Canvas(modifier = modifier.padding(16.dp)) {
        val centerOffset = Offset(10f, 10f)
        drawCircle(
            color = color.copy(alpha = 0.8f),
            radius = floatAnim,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = 0.4f),
            radius = floatAnim / 2,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = 0.2f),
            radius = floatAnim / 4,
            center = centerOffset,
        )
    }
}