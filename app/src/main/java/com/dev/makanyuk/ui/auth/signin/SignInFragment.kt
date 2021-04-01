package com.dev.makanyuk.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dev.makanyuk.R
import com.dev.makanyuk.app.MakanYuk
import com.dev.makanyuk.model.response.login.LoginResponse
import com.dev.makanyuk.ui.MainActivity
import com.dev.makanyuk.ui.auth.AuthActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(), SignInContract.View {

    private lateinit var signInPresenter: SignInPresenter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!MakanYuk.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        signInPresenter = SignInPresenter(this)
        initView()

        btn_register.setOnClickListener {
            val signUp = Intent(activity, AuthActivity::class.java)
            signUp.putExtra("page_request", 2)
            startActivity(signUp)
        }

        btn_sign_in.setOnClickListener {
            val email = et_email_address.text.toString().trim()
            val password = et_password.text.toString().trim()

            dataValidation(email, password)
        }
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog.apply {
            setContentView(dialogLayout)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun dataValidation(email: String, password: String) {
        if (email.isEmpty()) {
            et_email_address.error = resources.getString(R.string.email_validation)
            et_email_address.requestFocus()
        } else if (!isValidEmail(email)) {
            et_email_address.error = resources.getString(R.string.email_valid)
            et_email_address.requestFocus()
        } else if (password.isEmpty()) {
            et_password.error = resources.getString(R.string.password_validation)
            et_password.requestFocus()
        } else {
            signInPresenter.submitLogin(email, password)
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        loginResponse.access_token?.let { MakanYuk.getApp().setToken(it) }
        val gsonUser = Gson()
        val jsonUser = gsonUser.toJson(loginResponse.user)
        MakanYuk.getApp().setUser(jsonUser)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }
}