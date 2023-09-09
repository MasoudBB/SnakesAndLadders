package com.example.snakesandladders

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.snakesandladders.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Declare a late init variable 'binding' of type 'ActivityMainBinding'
    private lateinit var binding: ActivityMainBinding

    // Declare a late init variable 'soundEffect' of type 'SoundManager'
    private lateinit var soundEffect: SoundManager

    //state of MediaPlayer
    private var isMuteSound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the sound effects manager
        soundEffect = SoundManager(this)

        //play background music
        soundEffect.initBackgroundMusic(R.raw.home_sound)
        handleBackgroundMusic()

        // Configure the activity for fullscreen display
        enableFullScreen()

        // Attach click sound effect to UI buttons
        setupButtonClickListeners()

    }

    /**
     * Sets up click listeners for multiple buttons to play a click sound effect when clicked.
     */
    private fun setupButtonClickListeners() {

        binding.apply {
            // Assign the click listener to specific buttons
            btnMultiGame.setOnClickListener{
                soundEffect.playClickSoundEffect()
                startActivity(Intent(applicationContext,BoardGameActivity::class.java ))
            }
            btnBack.setOnClickListener{
                soundEffect.playClickSoundEffect()
            }
            btnRobotGame.setOnClickListener{
                soundEffect.playClickSoundEffect()
            }
            btnIntentToTelegram.setOnClickListener{
                soundEffect.playClickSoundEffect()
                intentToTelegram()
            }
            // You can add more buttons here as needed
        }
    }

    /**
     * Called when the activity is being destroyed.
     * Stops the background music to release resources.
     */
    override fun onDestroy() {
        super.onDestroy()
        soundEffect.stopBackgroundMusic()
    }

    /**
     * Handles the background music toggle button click.
     * Toggles between playing and pausing the background music.
     */
    private fun handleBackgroundMusic() {
        binding.btnMusic.setOnClickListener {
            if (isMuteSound) {
                soundEffect.mediaPlayer?.start() // Resume playback.
                binding.btnMusic.setImageResource(R.drawable.btn_music_spring) // Set button image for sound ON.
            } else {
                soundEffect.mediaPlayer?.pause() // Pause playback.
                binding.btnMusic.setImageResource(R.drawable.btn_no_music_spring) // Set button image for sound OFF.
            }
            isMuteSound = !isMuteSound // Toggle the mute state.
        }
    }

    /**
     * Obtains package information for the Telegram app.
     * Handles different behavior based on the Android version.
     */
    private fun getPackageInfo() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                "org.telegram.messenger",
                PackageManager.PackageInfoFlags.of(0)
            )
        } else {
            packageManager.getPackageInfo("org.telegram.messenger", 0)
        }
    }

    /**
     * Checks if the Telegram app is installed on the device.
     * @return True if the Telegram app is installed, false otherwise.
     */
    private fun isInitApp(): Boolean {

        return try {
            getPackageInfo() // Attempt to get package information for Telegram.
            true // Telegram is installed.
        } catch (e: Exception) {
            false // Telegram is not installed.
        }
    }

    /**
     * Initiates an intent to open the Telegram app or shows a message if Telegram is not installed.
     */
    private fun intentToTelegram() {
        if (isInitApp()) {
            // Telegram is installed, create an intent to open it.
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://t.me/MBB_MBB") // Specify the Telegram link.
            intent.setPackage("org.telegram.messenger") // Set the package name for Telegram.

            startActivity(intent) // Start the intent to open Telegram.
        } else {
            // Telegram is not installed, show a toast message.
            Toast.makeText(applicationContext, "تلگرام روی گوشی نصب نیست.", Toast.LENGTH_SHORT)
                .show()
        }
    }

}
