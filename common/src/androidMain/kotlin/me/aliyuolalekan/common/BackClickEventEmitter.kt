package me.aliyuolalekan.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BackClickEventEmitter {
    private val backClickFlow: MutableStateFlow<Unit?> = MutableStateFlow(null)

    val backClick: StateFlow<Unit?>
        get() = backClickFlow

    fun emitClick(value: Unit?) {
        backClickFlow.value = value
    }
}