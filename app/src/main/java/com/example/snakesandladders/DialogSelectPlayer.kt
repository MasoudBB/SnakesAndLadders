package com.example.snakesandladders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.snakesandladders.databinding.DialogSelectPlayerBinding

class DialogSelectPlayer: DialogFragment() {
    lateinit var binding: DialogSelectPlayerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSelectPlayerBinding.inflate(layoutInflater)
        return binding.root
    }

     override fun getTheme()= android.R.style.Theme_NoTitleBar_Fullscreen
}