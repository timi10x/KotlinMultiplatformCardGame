package me.aliyuolalekan.common

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.window.Dialog
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
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
    lateinit var appContext: Context

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(WhotPlusDatabase.Schema, appContext, "whotplus.db")
    }
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
        onDismissRequest = onDismissRequest
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
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
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

@Composable
actual fun getImagePainter(imagePath: ImagePath): Painter =
    painterResource(ImagePathToDrawableResourceMapper.mapPathToResource(imagePath))

actual fun getLogger(): Logger = AndroidLogger()

actual val backClickEvent: StateFlow<Unit?> = BackClickEventEmitter.backClick

@Composable
actual fun onBackClicked(windowsStackSize: Int): Unit {
    BackClickEventEmitter.emitClick(null)
    if (windowsStackSize < 2) {
        AppStateObserver.setShouldFinish(true)
    }
}

actual fun getGameSoundPlayer(): IGameSoundPlayer = AndroidGameSoundPlayer