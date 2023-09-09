package com.example.snakesandladders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BoardGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_game)
        showDialog()
    }

    private fun  showDialog(){
        val dialog = DialogSelectPlayer()
        dialog.show(supportFragmentManager,null)
    }
}