package me.aliyuolalekan.common

import android.util.Log
import me.aliyuolalekan.common.logger.LogKey
import me.aliyuolalekan.common.logger.Logger

class AndroidLogger : Logger() {

    override fun <T> logError(data: T) {
        Log.e(LogKey.ERROR, "$data")
    }

    override fun <T> logDebug(data: T) {
        Log.i(LogKey.DEBUG, "$data")
    }

    override fun <T> logWarn(data: T) {
        Log.w(LogKey.WARNING, "$data")
    }
}