package me.aliyuolalekan.common

import me.aliyuolalekan.common.logger.LogKey
import me.aliyuolalekan.common.logger.Logger
import org.jetbrains.skia.impl.Log

class DesktopLogger : Logger() {
    override fun <T> logError(data: T) {
        Log.error("${LogKey.ERROR}$data")
    }

    override fun <T> logDebug(data: T) {
        Log.info("${LogKey.DEBUG}$data")
    }

    override fun <T> logWarn(data: T) {
        Log.warn("${LogKey.WARNING}$data")
    }
}