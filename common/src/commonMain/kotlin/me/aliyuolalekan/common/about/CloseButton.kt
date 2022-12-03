package me.aliyuolalekan.common.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.aliyuolalekan.common.ui.standardContentMargin

@Composable
fun CloseButton(onClose: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier.padding(standardContentMargin)
            .wrapContentWidth().wrapContentHeight()
            .clickable { onClose() }
    ) {
        Icon(
            Icons.Default.Close,
            contentDescription = null,
            tint = Color.Black
        )
    }
}