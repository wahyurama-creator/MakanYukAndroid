package com.dev.makanyuk.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.dev.makanyuk.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == 2) {
            toolbarSignUp()
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.signInFragment, true)
                .build()

            Navigation.findNavController(
                findViewById(R.id.fragmentAuth)
            )
                .navigate(R.id.action_signInFragment_to_signUpFragment, null, navOption)
        }
    }

    private fun toolbarSignUp() {
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register and eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun toolbarSignUpAddress() {
        toolbar.title = "Address"
        toolbar.subtitle = "Make sure it's valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back, null)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun toolbarSignUpSuccess() {
        toolbar.visibility = View.GONE
    }
}