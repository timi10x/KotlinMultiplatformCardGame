package me.aliyuolalekan.common.ingame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.aliyuolalekan.common.Strings
import me.aliyuolalekan.common.domain.IGameRepository
import me.aliyuolalekan.common.domain.model.formattedName
import me.aliyuolalekan.common.ui.standardButtonPadding

/**
 * This is a temporal solution for Toasts. For a proper solution, we would
 * A ToastManager that will accept stack of messages and will keep popping
 * until empty with delay during pop.
 */

sealed class ToastDuration {
    object SHORT : ToastDuration()
    object LONG : ToastDuration()
}

fun ToastDuration.toLong() = this.let {
    when (it) {
        ToastDuration.SHORT -> 1000L
        ToastDuration.LONG -> 2000L
    }
}

@Composable
fun ToastBox(repository: IGameRepository) {
    val wrongCard by repository.gameStateFlow.map { it.invalidCardPlayed }.collectAsState(null)
    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight(), contentAlignment = Alignment.Center) {
        ToastMessage(
            message = "${Strings.INVALID_CARD_SELECTED_MESSAGE} ${wrongCard?.formattedName()}",
            shouldShow = wrongCard != null,
            duration = ToastDuration.SHORT
        ) {
            repository.clearWrongCardSelected()
        }
    }
}

/**
 * @param onShown is a callback to hide the visibility of #ToastMessage Composable
 */
@Composable
fun ToastMessage(message: String, shouldShow: Boolean, duration: ToastDuration, onShown: () -> Unit) {
    val scope = rememberCoroutineScope()
    if (shouldShow) {
        Box(
            modifier = Modifier.background(Color.White)
                .clip(RoundedCornerShape(standardButtonPadding))
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(standardButtonPadding)
        ) {
            Text(message, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.Center))
        }
        scope.launch {
            delay(duration.toLong())
            onShown()
        }
    }
}
