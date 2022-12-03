package me.aliyuolalekan.common.homescreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import me.aliyuolalekan.common.GameDialog
import me.aliyuolalekan.common.about.AboutPage
import me.aliyuolalekan.common.domain.model.GameSettings
import me.aliyuolalekan.common.ingame.ui.InGameScreen
import me.aliyuolalekan.common.servicelocator.ServiceLocator
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.splashscreen.windowsManager
import me.aliyuolalekan.common.ui.ActionButton
import me.aliyuolalekan.common.ui.Avatar
import me.aliyuolalekan.common.ui.CustomCard
import me.aliyuolalekan.common.ui.VerticalSpace
import me.aliyuolalekan.common.ui.darkBrown
import me.aliyuolalekan.common.ui.getButtonWidth
import me.aliyuolalekan.common.ui.lightBrown
import me.aliyuolalekan.common.ui.standardContentMargin
import me.aliyuolalekan.common.ui.typography

val repository = ServiceLocator.localGameRepository

@Composable
fun GameHome(settings: GameSettings, onSaveSettingsClick: (GameSettings) -> Unit) {
    // TODO refactor state
    val gameState by repository.gameStateFlow.collectAsState()

    if (!gameState.gameStarted) {
        repository.playSound(GameSound.GAME_START)
    }

    val gameSettings = mutableStateOf(settings)
    val isSinglePlayerInitValue = gameSettings.value.isSinglePlayer
    var showCustomDialogWithResult by remember { mutableStateOf(false) }
    val isSinglePlayer = remember { mutableStateOf(isSinglePlayerInitValue) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(standardContentMargin)) {
            Avatar()
            VerticalSpace()
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                VerticalSpace()
                HomeScreenCardSpread()
                GameMenu(
                    onNewGameClick = { windowsManager.pushScreen { InGameScreen() } },
                    onGameModeClick = { showCustomDialogWithResult = !showCustomDialogWithResult },
                    onAboutGameClick = { windowsManager.pushScreen { AboutPage { windowsManager.popBackStack() } } }
                )
            }
        }
    }
    if (showCustomDialogWithResult) {
        GameDialog(
            "Game Modes",
            DialogText.SAVE,
            DialogText.CANCEL,
            onCancelClick = { showCustomDialogWithResult = !showCustomDialogWithResult },
            onOkayClick = {
                onSaveSettingsClick(settings.copy(isSinglePlayer = isSinglePlayer.value))
                showCustomDialogWithResult = !showCustomDialogWithResult
            },
            onDismissRequest = { showCustomDialogWithResult = !showCustomDialogWithResult }
        ) {
            GameModes(isSinglePlayer)
        }
    }
}

@Composable
fun GameModes(isSinglePlayer: MutableState<Boolean>) {
    Column(modifier = Modifier.wrapContentHeight().padding(standardContentMargin)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isSinglePlayer.value, onCheckedChange = {
                isSinglePlayer.value = it
            })
            Text("Single Player")
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Checkbox(checked = !isSinglePlayer.value, onCheckedChange = {
                    isSinglePlayer.value = !it
                })
            }
            Text("Multi - Player")
        }
        if (!isSinglePlayer.value) {
            TextField(
                "NOT Implemented",
                label = { Text("Enter Number of Players 2 - 5") },
                onValueChange = { /** TODO fix game mode **/ },
            )
        }
    }
}

@Composable
fun GameMenu(
    onNewGameClick: () -> Unit = {},
    onContinueGameClick: () -> Unit = {},
    onGameModeClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onGameHistoryClick: () -> Unit = {},
    onAboutGameClick: () -> Unit = {}
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .padding(20.dp)
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            ).onSizeChanged { size = it }
    ) {
        ActionButton(
            text = GameMenuText.NEW_GAME,
            modifier = Modifier.width(getButtonWidth(size.width.dp)).wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            onClick = onNewGameClick
        )

        // TODO UnComment this block when continue is implemented.
//        ActionButton(
//            text = GameMenuText.CONTINUE,
//            modifier = Modifier.width(getButtonWidth(size.width.dp)).wrapContentHeight()
//                .align(Alignment.CenterHorizontally),
//            onClick = onContinueGameClick
//        )
//        ActionButton(
//            text = GameMenuText.GAME_MODE,
//            modifier = Modifier.width(getButtonWidth(size.width.dp)).wrapContentHeight()
//                .align(Alignment.CenterHorizontally),
//            onClick = onGameModeClick
//        )
        ActionButton(
            text = GameMenuText.SETTINGS,
            modifier = Modifier.width(getButtonWidth(size.width.dp)).wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            onClick = onSettingsClick
        )
        ActionButton(
            text = GameMenuText.GAME_HISTORY,
            modifier = Modifier.width(getButtonWidth(size.width.dp)).wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            onClick = onGameHistoryClick
        )
        ActionButton(
            text = GameMenuText.ABOUT,
            modifier = Modifier.width(getButtonWidth(size.width.dp)).wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            onClick = onAboutGameClick
        )
    }
}

@Composable
private fun HomeScreenCardSpread() {

    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            CustomCard(
                backgroundColor = darkBrown, elevation = 1.dp, modifier = Modifier
                    .size(height = 120.dp, width = 140.dp)
                    .align(Alignment.TopStart)
            ) {
                Column(modifier = Modifier.padding(start = 10.dp), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "W", style = typography.h5, fontWeight = FontWeight.SemiBold, color = lightBrown
                    )
                }
            }
            CustomCard(
                backgroundColor = lightBrown, elevation = 1.dp, modifier = Modifier
                    .size(height = 120.dp, width = 140.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 40.dp)
            ) {
                Column(modifier = Modifier.padding(start = 10.dp), verticalArrangement = Arrangement.Center) {
                    Text(text = "H", style = typography.h5, fontWeight = FontWeight.SemiBold)
                }
            }
            CustomCard(
                backgroundColor = darkBrown, elevation = 1.dp, modifier = Modifier
                    .size(height = 120.dp, width = 180.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 80.dp)
            ) {
                Column(modifier = Modifier.padding(start = 10.dp), verticalArrangement = Arrangement.Center) {
                    Text(text = "O", style = typography.h5, fontWeight = FontWeight.SemiBold, color = lightBrown)
                }
            }

            CustomCard(
                backgroundColor = lightBrown, elevation = 1.dp, modifier = Modifier
                    .size(height = 120.dp, width = 200.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 120.dp)
            ) {
                Column(modifier = Modifier.padding(start = 10.dp), verticalArrangement = Arrangement.Center) {
                    Text(text = "T", style = typography.h5, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

// TODO investigate how to move this to localizable settings.
private object GameMenuText {
    const val NEW_GAME = "New Game"
    const val CONTINUE = "Continue"
    const val GAME_MODE = "Game Mode"
    const val SETTINGS = "Settings"
    const val GAME_HISTORY = "History"
    const val ABOUT = "About Game"
}

private object DialogText {
    const val SAVE = "Save"
    const val CANCEL = "Cancel"
}