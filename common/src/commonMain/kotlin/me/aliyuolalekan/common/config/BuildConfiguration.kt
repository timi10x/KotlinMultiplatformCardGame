package me.aliyuolalekan.common.config

object BuildConfiguration {

    // Change to release to influence change
    val buildConfigType = AppBuildType.DEVELOP
}

fun BuildConfiguration.isDevelop() = buildConfigType == AppBuildType.DEVELOP

enum class AppBuildType {
    DEVELOP, RELEASE
}