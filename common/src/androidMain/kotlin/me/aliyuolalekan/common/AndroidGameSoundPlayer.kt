package me.aliyuolalekan.common

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import me.aliyuolalekan.common.sound.GameSound
import me.aliyuolalekan.common.sound.IGameSoundPlayer

private const val LEFT_VOLUME = 1f
private const val RIGHT_VOLUME = 1f
private const val SOUND_PRIORITY = 1
private const val LOOP_SOUND = 0
private const val PLAY_RATE = 1f

object AndroidGameSoundPlayer : IGameSoundPlayer {

    private val gameSoundMap = mutableMapOf<GameSound, Int>()

    private var soundPool: SoundPool? = null

    private val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    fun init(context: Context) {
        soundPool = SoundPool.Builder().setAudioAttributes(audioAttributes).build()
        soundPool?.load(context, R.raw.pick_two, SOUND_PRIORITY)?.let { gameSoundMap[GameSound.PICK_TWO] = it }
        soundPool?.load(context, R.raw.pick_three, SOUND_PRIORITY)?.let { gameSoundMap[GameSound.PICK_THREE] = it }
        soundPool?.load(context, R.raw.hold_on, SOUND_PRIORITY)?.let { gameSoundMap[GameSound.HOLD_ON] = it }
        soundPool?.load(context, R.raw.general_market, SOUND_PRIORITY)?.let {
            gameSoundMap[GameSound.GENERAL_MARKET] = it
        }
        soundPool?.load(context, R.raw.king_request, SOUND_PRIORITY)?.let { gameSoundMap[GameSound.KING_REQUEST] = it }
        soundPool?.load(context, R.raw.game_start, SOUND_PRIORITY)?.let { gameSoundMap[GameSound.GAME_START] = it }
        soundPool?.load(context, R.raw.game_over, SOUND_PRIORITY)?.let { gameSoundMap[GameSound.GAME_OVER] = it }
        soundPool?.load(context, R.raw.pick_from_market, SOUND_PRIORITY)?.let {
            gameSoundMap[GameSound.PICK_FROM_MARKET] = it
        }
    }

    override fun play(gameSound: GameSound) {
        gameSoundMap[gameSound]?.let {
            soundPool?.play(it, LEFT_VOLUME, RIGHT_VOLUME, SOUND_PRIORITY, LOOP_SOUND, PLAY_RATE)
        }
    }

    fun release() {
        soundPool?.release()
        soundPool = null
    }
}