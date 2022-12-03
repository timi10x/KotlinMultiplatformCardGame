package me.aliyuolalekan.common.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val smallPadding = 4.dp
val standardContentMargin = 16.dp
val standardLargeMargin = 24.dp
val standardButtonPadding = 8.dp
val standardElevation = 8.dp
val standardAvatarSize = 90.dp
val standardCardCorners = 12.dp
val standardDialogButtonSeparator = 4.dp

val opponentContentWidth = 60.dp
val opponentContentHeight = 100.dp

val standardTextSize = 14.sp
val standardTitleTextSize = 18.sp
val standardBodyTitle = 16.sp
val standardButtonTextSize = 16.sp

val playerCardWidth = 90.dp
val playerCardHeight = 120.dp
val playerCardElevation = 2.dp

val marketCardWidth = 90.dp
val marketCardHeight = 120.dp
val marketCardElevation = 8.dp

val playAreaCardWidth = 120.dp
val playAreaCardHeight = 160.dp
val playAreaCardElevation = 14.dp

val normalAvatarSize = 55.dp
val normalAvatarIconSize = 55.dp

fun getButtonWidth(screenWidth: Dp): Dp =
    if (screenWidth < 600.dp) {
        200.dp
    } else {
        400.dp
    }

val dialogHeight = 280.dp

val standardGameOverScreenSize = 300.dp