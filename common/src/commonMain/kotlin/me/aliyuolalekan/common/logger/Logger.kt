package me.aliyuolalekan.common.logger

import me.aliyuolalekan.common.config.AppBuildType
import me.aliyuolalekan.common.config.BuildConfiguration
import me.aliyuolalekan.common.servicelocator.ServiceLocator

abstract class Logger {
    abstract fun <T> logError(data: T)
    abstract fun <T> logDebug(data: T)
    abstract fun <T> logWarn(data: T)

    fun <T> error(data: T) {
       withBuildType(AppBuildType.DEVELOP) {
           logError(data)
       }
    }

    fun <T> warn(data: T) {
       withBuildType(AppBuildType.RELEASE) {
           logWarn(data)
       }
    }

    fun <T> debug(data: T) {
        withBuildType(AppBuildType.DEVELOP) {
            logDebug(data)
        }
    }

    private fun withBuildType(appBuildType: AppBuildType, action: () -> Unit) {
        if (BuildConfiguration.buildConfigType == appBuildType) {
            action()
        }
    }
}

object LogKey {
    const val ERROR = "ERROR: "
    const val WARNING = "WARNING: "
    const val DEBUG = "DEBUG: "
}

val logger = ServiceLocator.logger
