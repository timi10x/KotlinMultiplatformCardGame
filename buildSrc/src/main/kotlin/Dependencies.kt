object versions {
    const val appVersion = "1.0"
    const val kotlinVersion = "1.5.0"
    const val jUnit5Version = "5.8.0"
    const val turbineVersion = "0.7.0"
    const val appcompatVersion = "1.4.0"
    const val coreKtxVersion = "1.7.0"
    const val jUnit4Version = "4.13.2"
    const val kotlinGradlePluginVersion = "1.5.31"
    const val gradleVersion = "4.2.2"
    const val composePluginVersion = "1.0.0-beta5"
    const val jvmTargetVersion = "11"
    const val packageVersion = "1.0.0"
    const val sqlDelightVersion = "1.5.3"
    const val mockkVersion = "1.12.1"
    const val materialComposeVersion = "1.1.0-beta03"
    const val activityComposeVersion = "1.4.0"
}

object Dependencies {
    object commonMain {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinVersion}"
        const val sqlDelight = "com.squareup.sqldelight:runtime:${versions.sqlDelightVersion}"
        const val sqlDelightCoroutines = "com.squareup.sqldelight:coroutines-extensions:${versions.sqlDelightVersion}"
        const val materialCompose = "androidx.compose.material:material:${versions.materialComposeVersion}"
        const val activityCompose = "androidx.activity:activity-compose:${versions.activityComposeVersion}"
    }

    object commonTest {
        const val junit5Api = "org.junit.jupiter:junit-jupiter-api:${versions.jUnit5Version}"
        const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:${versions.jUnit5Version}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${versions.kotlinVersion}"
        const val turbineTest = "app.cash.turbine:turbine:${versions.turbineVersion}"
        const val mockk = "io.mockk:mockk:${versions.mockkVersion}"
    }

    object androidMain {
        const val appCompat = "androidx.appcompat:appcompat:${versions.appcompatVersion}"
        const val androidXCore = "androidx.core:core-ktx:${versions.coreKtxVersion}"
        const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:${versions.sqlDelightVersion}"
        const val activityCompose = "androidx.activity:activity-compose:${versions.activityComposeVersion}"
    }

    object nativeMain {
        val sqlDelightDriver = "com.squareup.sqldelight:sqlite-driver:${versions.sqlDelightVersion}"
    }

    object androidTest {
        const val junit4 = "junit:junit:${versions.jUnit4Version}"
        const val mockk = "io.mockk:mockk:${versions.mockkVersion}"
    }

    object global {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlinGradlePluginVersion}"
        const val gradle = "com.android.tools.build:gradle:${versions.gradleVersion}"
        const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${versions.sqlDelightVersion}"
    }
}