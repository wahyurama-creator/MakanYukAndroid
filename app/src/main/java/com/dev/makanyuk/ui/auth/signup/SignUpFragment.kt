package com.dev.makanyuk.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dev.makanyuk.R
import com.dev.makanyuk.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_continue.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_signUpFragment_to_signUpAddressFragment, null)

            (activity as AuthActivity).toolbarSignUpAddress()
        }
    }
}