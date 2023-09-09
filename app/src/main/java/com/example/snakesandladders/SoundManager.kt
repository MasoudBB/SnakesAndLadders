package com.example.snakesandladders

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool

/**
 * A class that manages sound effects and background music for the application.
 *
 * @param context The application context.
 */
class SoundManager(private val context: Context) {
    // Define audio attributes for sound effects
    private val audioAttributes =
        AudioAttributes
            .Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

    // Initialize the SoundPool for playing sound effects
    private val soundPool = SoundPool
        .Builder()
        .setMaxStreams(10)
        .setAudioAttributes(audioAttributes)
        .build()

    // Load sound effect resources into variables
    private val soundEffectClick = soundPool.load(context, R.raw.click, 1)
    private val soundEffectRollDice = soundPool.load(context, R.raw.roll_dice, 1)

    /**
     * Plays the click sound effect.
     */
    fun playClickSoundEffect() {
        soundPool.play(soundEffectClick, 1F, 1F, 0, 0, 1F)
    }

    /**
     * Plays the roll dice sound effect.
     */
    fun playRollDiceSoundEffect() {
        soundPool.play(soundEffectRollDice, 1F, 1F, 0, 0, 1F)
    }

    // Initialize a MediaPlayer for background music
    var mediaPlayer: MediaPlayer? = null

    /**
     * Initializes and starts the background music.
     *
     * @param resourceId The resource ID of the background music to play.
     */
    fun initBackgroundMusic(resourceId: Int) {
        mediaPlayer = MediaPlayer.create(
            context,
            resourceId
        ) // Create MediaPlayer with the provided resource.
        mediaPlayer?.isLooping = true // Set the music to loop.
        mediaPlayer?.setVolume(0.2f, 0.2f) // Set the volume (adjust as needed).
        mediaPlayer?.start() // Start playing the background music.
    }

    /**
     * Stops the background music and releases the MediaPlayer resources.
     */
    fun stopBackgroundMusic() {
        mediaPlayer?.apply {
            if (isPlaying) stop()   // Pause the playback.
            reset()                 // Stop the MediaPlayer.
            release()               // Release the MediaPlayer resources.
        }
        mediaPlayer = null          // Set the MediaPlayer reference to null.
    }
}
