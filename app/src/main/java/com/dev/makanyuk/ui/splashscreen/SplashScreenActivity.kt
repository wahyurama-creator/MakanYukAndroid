package com.dev.makanyuk.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dev.makanyuk.MainActivity
import com.dev.makanyuk.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 4000)
    }
}