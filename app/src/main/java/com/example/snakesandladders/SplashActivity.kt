package com.example.snakesandladders

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    // Declare a late init variable 'soundEffect' of type 'SoundManager' to manage sound effects
    private lateinit var soundEffect: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Enable fullscreen mode (Implemented in Utils)
        enableFullScreen()

        // Initialize the sound effects manager
        soundEffect = SoundManager(this)


        // Play the sound effect for rolling dice after a delay
        playRollDiceSound()

        // Navigate to the main activity after a delay
        goToMainActivity()
    }

    // Play the sound effect for rolling dice after a delay
    private fun playRollDiceSound() {
        delayHandler(1300) {
            soundEffect.playRollDiceSoundEffect()
        }
    }

    // Navigate to the main activity after a delay
    private fun goToMainActivity() {
        delayHandler(4000) {
            // Start the main activity and finish the splash activity
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

}
