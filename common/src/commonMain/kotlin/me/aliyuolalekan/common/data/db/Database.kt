package me.aliyuolalekan.common.data.db

import me.aliyuolalekan.WhotPlusDatabase
import me.aliyuolalekan.common.DriverFactory.createDriver

fun createWhotPlusDatabase(): WhotPlusDatabase = WhotPlusDatabase(createDriver())