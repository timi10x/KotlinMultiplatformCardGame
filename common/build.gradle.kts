import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version versions.composePluginVersion
    id("com.android.library")
    id("com.squareup.sqldelight")
}

group = "me.aliyuolalekan"
version = versions.appVersion

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = versions.jvmTargetVersion
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                implementation(Dependencies.commonMain.coroutinesCore)
                implementation(Dependencies.commonMain.sqlDelight)
                implementation(Dependencies.commonMain.sqlDelightCoroutines)
                implementation(Dependencies.commonTest.mockk)
                implementation(Dependencies.commonMain.materialCompose)
                implementation(Dependencies.commonMain.activityCompose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Dependencies.commonTest.junit5Api)
                implementation(Dependencies.commonTest.junit5Engine)
                implementation(Dependencies.commonTest.coroutinesTest)
                implementation(Dependencies.commonTest.turbineTest)
                implementation(Dependencies.commonTest.mockk)
            }
        }
        val androidMain by getting {
            dependencies {
                api(Dependencies.androidMain.appCompat)
                api(Dependencies.androidMain.androidXCore)
                implementation(Dependencies.androidMain.sqlDelightAndroidDriver)
                implementation(Dependencies.androidMain.activityCompose)
                implementation(Dependencies.commonMain.activityCompose)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Dependencies.androidTest.junit4)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation(Dependencies.nativeMain.sqlDelightDriver)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sqldelight {
    database("WhotPlusDatabase") {
        packageName = "me.aliyuolalekan"
    }
}