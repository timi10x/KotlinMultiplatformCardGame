package me.aliyuolalekan.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.flow.StateFlow
import me.aliyuolalekan.common.domain.model.ImagePath
import me.aliyuolalekan.common.logger.Logger
import me.aliyuolalekan.common.sound.IGameSoundPlayer

expect object DriverFactory {
    fun createDriver(): SqlDriver
}

@Composable
expect fun ActionDialog(
    title: String,
    subTitle: String,
    okayText: String,
    cancelText: String,
    onOkayClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit
)

@Composable
expect fun GameDialog(
    title: String,
    okayText: String,
    cancelText: String,
    onOkayClick: () -> Unit,
    onCancelClick: () -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
)

@Composable
expect fun getImagePainter(imagePath: ImagePath): Painter

expect fun getLogger(): Logger

expect val backClickEvent: StateFlow<Unit?>

@Composable
expect fun onBackClicked(windowsStackSize: Int)

expect fun getGameSoundPlayer(): IGameSoundPlayer
