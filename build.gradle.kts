buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.global.kotlinGradlePlugin)
        classpath(Dependencies.global.gradle)
        classpath(Dependencies.global.sqlDelight)
    }
}

group = "me.aliyuolalekan"
version = versions.appVersion

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}