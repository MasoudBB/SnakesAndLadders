package com.example.snakesandladders

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

// Configure the activity for fullscreen display
fun Activity.enableFullScreen(){
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}
// A helper function for adding a delay before executing an action
fun delayHandler(delay: Long, action: () -> Unit) {
    Handler(Looper.myLooper()!!).postDelayed({
        action.invoke()

    }, delay)
}
