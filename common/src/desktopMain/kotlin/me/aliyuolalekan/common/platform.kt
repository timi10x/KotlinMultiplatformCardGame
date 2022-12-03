package me.aliyuolalekan.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.window.Dialog
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.aliyuolalekan.WhotPlusDatabase
import me.aliyuolalekan.common.domain.model.ImagePath
import me.aliyuolalekan.common.logger.Logger
import me.aliyuolalekan.common.sound.IGameSoundPlayer
import me.aliyuolalekan.common.ui.VerticalSpace
import me.aliyuolalekan.common.ui.dialogHeight
import me.aliyuolalekan.common.ui.standardButtonPadding
import me.aliyuolalekan.common.ui.standardButtonTextSize
import me.aliyuolalekan.common.ui.standardCardCorners
import me.aliyuolalekan.common.ui.standardDialogButtonSeparator
import me.aliyuolalekan.common.ui.standardElevation
import me.aliyuolalekan.common.ui.typography

actual object DriverFactory {
    actual fun createDriver(): SqlDriver =
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply { WhotPlusDatabase.Schema.create(this) }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun ActionDialog(
    title: String,
    subTitle: String,
    okayText: String,
    cancelText: String,
    onOkayClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            Text(title, style = typography.h6)
        },
        text = {
            Text(subTitle, fontStyle = FontStyle.Normal)
        },
        buttons = {
            okayCancelButton(okayText, cancelText, onOkayClick, onCancelClick)
        }
    )
}

@Composable
actual fun GameDialog(
    title: String,
    okayText: String,
    cancelText: String,
    onOkayClick: () -> Unit,
    onCancelClick: () -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onCloseRequest = onDismissRequest
    ) {
        Card(
            elevation = standardElevation,
            shape = RoundedCornerShape(standardCardCorners),
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        ) {
            Column(modifier = Modifier.padding(standardButtonPadding).fillMaxWidth().height(dialogHeight)) {
                Text(title, style = typography.h6)
                content()
                VerticalSpace()
                okayCancelButton(okayText, cancelText, onOkayClick, onCancelClick)
            }
        }
    }
}

@Composable
fun okayCancelButton(
    okayText: String,
    cancelText: String,
    onOkayClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(all = standardButtonPadding),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = onCancelClick,
            modifier = Modifier.wrapContentWidth().wrapContentHeight()
        ) {
            Text(cancelText, fontSize = standardButtonTextSize)
        }
        Spacer(Modifier.padding(standardDialogButtonSeparator))
        Button(
            onClick = onOkayClick,
            modifier = Modifier.wrapContentWidth().wrapContentHeight()
        ) {
            Text(okayText, fontSize = standardButtonTextSize)
        }
    }
}

private const val prefix = "images/"
private const val suffix = ".png"

@Composable
actual fun getImagePainter(imagePath: ImagePath): Painter = painterResource("$prefix$imagePath$suffix")

actual fun getLogger(): Logger = DesktopLogger()

actual val backClickEvent: StateFlow<Unit?> = MutableStateFlow(null)

@Composable
actual fun onBackClicked(windowsStackSize: Int): Unit = (Unit)

actual fun getGameSoundPlayer(): IGameSoundPlayer = DesktopGameSoundPlayer

