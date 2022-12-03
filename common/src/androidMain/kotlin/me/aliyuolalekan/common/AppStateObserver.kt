package me.aliyuolalekan.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AppStateObserver {
    private val shouldFinishMutableState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val shouldFinish: StateFlow<Boolean>
        get() = shouldFinishMutableState

    fun setShouldFinish(shouldFinish: Boolean) {
        shouldFinishMutableState.value = shouldFinish
    }
}