package me.aliyuolalekan.common

import java.io.File
import javax.sound.sampled.AudioSystem
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.sound.IGameSoundPlayer

object DesktopGameSoundPlayer : IGameSoundPlayer {

    override fun play(gameSound: GameSound) {
        resolvePath(gameSound)?.let { audioFile ->
            val audioInputStream = AudioSystem.getAudioInputStream(audioFile)
            val clip = AudioSystem.getClip()
            clip.open(audioInputStream)
            clip.start()
        }
    }
}

private fun resolvePath(gameSound: GameSound): File? {
    val file = File(getSound(gameSound))
    if (file.exists()) {
        return file
    }
    return null
}

private fun getSound(gameSound: GameSound): String =
    when (gameSound) {
        GameSound.GAME_OVER -> "./src/jvmMain/resources/sounds/game_over.wav"
        GameSound.GAME_START -> "./src/jvmMain/resources/sounds/game_start.wav"
        GameSound.GENERAL_MARKET -> "./src/jvmMain/resources/sounds/general_market.wav"
        GameSound.HOLD_ON -> "./src/jvmMain/resources/sounds/hold_on.wav"
        GameSound.PICK_TWO -> "./src/jvmMain/resources/sounds/pick_two.wav"
        GameSound.PICK_THREE -> "./src/jvmMain/resources/sounds/pick_three.wav"
        GameSound.KING_REQUEST -> "./src/jvmMain/resources/sounds/king_request.wav"
        GameSound.PICK_FROM_MARKET -> "./src/jvmMain/resources/sounds/pick_from_market.wav"
    }
