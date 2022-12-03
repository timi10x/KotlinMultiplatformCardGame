package me.aliyuolalekan.android

import android.app.Application
import me.aliyuolalekan.common.DriverFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DriverFactory.appContext = this
    }
}