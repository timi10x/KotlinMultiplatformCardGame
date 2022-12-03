package me.aliyuolalekan.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.aliyuolalekan.common.AndroidGameSoundPlayer
import me.aliyuolalekan.common.App
import me.aliyuolalekan.common.AppStateObserver
import me.aliyuolalekan.common.BackClickEventEmitter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidGameSoundPlayer.init(this)
        setContent { App() }
    }

    override fun onDestroy() {
        super.onDestroy()
        AndroidGameSoundPlayer.release()
    }

    override fun onBackPressed() {
        BackClickEventEmitter.emitClick(Unit)
        finishAppIfNeeded()
    }

    private fun finishAppIfNeeded() {
        lifecycleScope.launch {
            AppStateObserver.shouldFinish.collect { shouldClose ->
                if (shouldClose) {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            }
        }
    }
}