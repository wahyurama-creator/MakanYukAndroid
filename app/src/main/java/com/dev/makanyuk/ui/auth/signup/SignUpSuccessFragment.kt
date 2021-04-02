package com.dev.makanyuk.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dev.makanyuk.R
import com.readystatesoftware.chuck.internal.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_sign_up_success.*

class SignUpSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_find_food.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finishAffinity()
        }
    }
}