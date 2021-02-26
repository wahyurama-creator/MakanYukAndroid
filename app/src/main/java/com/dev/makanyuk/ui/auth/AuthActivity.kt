package com.dev.makanyuk.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.dev.makanyuk.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == 2) {
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.signInFragment, true)
                .build()

            Navigation.findNavController(
                findViewById(R.id.fragmentAuth)
            )
                .navigate(R.id.action_signInFragment_to_signUpFragment, null, navOption)
        }
    }
}