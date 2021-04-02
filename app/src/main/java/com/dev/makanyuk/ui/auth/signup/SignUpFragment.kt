package com.dev.makanyuk.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dev.makanyuk.R
import com.dev.makanyuk.model.request.RegisterRequest
import com.dev.makanyuk.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    private lateinit var filePath: Uri

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListener()
    }

    private fun dataValidation(name: String, email: String, password: String) {
        if (name.isEmpty()) {
            et_full_name.error = resources.getString(R.string.name_validation)
            et_full_name.requestFocus()
        } else if (!isValidEmail(email)) {
            et_email_address.error = resources.getString(R.string.email_valid)
            et_email_address.requestFocus()
        } else if (email.isEmpty()) {
            et_email_address.error = resources.getString(R.string.name_validation)
            et_email_address.requestFocus()
        } else if (password.isEmpty()) {
            et_password.error = resources.getString(R.string.password_validation)
            et_password.requestFocus()
        } else if (password.length < 8) {
            et_password.error = resources.getString(R.string.password_length)
            et_password.requestFocus()
        } else {
            val data = RegisterRequest()
            data.name = name
            data.email = email
            data.password = password
            data.password_confirmation = password
            data.filePath = filePath

            val bundle = Bundle()
            bundle.putParcelable(EXTRA_DATA, data)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_signUpFragment_to_signUpAddressFragment, bundle)
        }
    }

    private fun initListener() {
        iv_add_photo.setOnClickListener {
            ImagePicker.with(this)
                .start()
        }
        btn_continue.setOnClickListener {
            val name = et_full_name.text.toString()
            val email = et_email_address.text.toString().trim()
            val password = et_password.text.toString().trim()
            dataValidation(name, email, password)
            (activity as AuthActivity).toolbarSignUpAddress()
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                filePath = data?.data!!
                Glide.with(this)
                    .load(filePath)
                    .circleCrop()
                    .into(iv_add_photo)
            }
            ImagePicker.RESULT_ERROR -> {
                Snackbar.make(requireView(), ImagePicker.getError(data), Snackbar.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Snackbar.make(requireView(), "Task cancelled", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}