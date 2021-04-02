package com.dev.makanyuk.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dev.makanyuk.R
import com.dev.makanyuk.app.MakanYuk
import com.dev.makanyuk.model.request.RegisterRequest
import com.dev.makanyuk.model.response.login.LoginResponse
import com.dev.makanyuk.ui.auth.AuthActivity
import com.dev.makanyuk.ui.auth.signup.SignUpFragment.Companion.EXTRA_DATA
import com.dev.makanyuk.ui.auth.signup.api.SignUpContract
import com.dev.makanyuk.ui.auth.signup.api.SignUpPresenter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_up_address.*

class SignUpAddressFragment : Fragment(), SignUpContract.View {

    private lateinit var registerRequest: RegisterRequest
    private lateinit var signUpPresenter: SignUpPresenter
    private lateinit var progressDialog: Dialog

    companion object {
        private val TAG = SignUpAddressFragment::class.java.simpleName
        private const val DIGIT_ONLY = "Phone number is not valid"
        private const val PHONE_IS_EMPTY = "Phone number must be filled"
        private const val ADDRESS_IS_EMPTY = "Address must be filled"
        private const val HOUSE_NO__IS_EMPTY = "House number must be filled"
        private const val CITY_IS_EMPTY = "City must be filled"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        signUpPresenter = SignUpPresenter(this)
        registerRequest = arguments?.getParcelable<RegisterRequest>(EXTRA_DATA) as RegisterRequest

        initView()
        initListener()
    }

    private fun initListener() {
        btn_register_now.setOnClickListener {
            val phoneNumber = et_phone_number.text.toString().trim()
            val address = et_address.text.toString()
            val houseNumber = et_house_number.text.toString()
            val city = et_city.text.toString()

            dataValidation(phoneNumber, address, houseNumber, city)
        }
    }

    private fun dataValidation(
        phoneNumber: String,
        address: String,
        houseNumber: String,
        city: String
    ) {
        if (!phoneNumber.isDigitsOnly() || phoneNumber.length < 11) {
            et_phone_number.error = DIGIT_ONLY
            et_phone_number.requestFocus()
        } else if (phoneNumber.isEmpty()) {
            et_phone_number.error = PHONE_IS_EMPTY
            et_phone_number.requestFocus()
        } else if (address.isEmpty()) {
            et_address.error = ADDRESS_IS_EMPTY
            et_address.requestFocus()
        } else if (houseNumber.isEmpty()) {
            et_house_number.error = HOUSE_NO__IS_EMPTY
            et_house_number.requestFocus()
        } else if (city.isEmpty()) {
            et_city.error = CITY_IS_EMPTY
            et_city.requestFocus()
        } else {
            registerRequest.apply {
                this.address = address
                this.city = city
                this.houseNumber = houseNumber
                this.phoneNumber = phoneNumber
            }
//            signUpPresenter.submitRegister(registerRequest, requireView())
            signUpPresenter.submitPhotoRegister(registerRequest.filePath!!, requireView())
        }
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog.apply {
            this.setContentView(dialogLayout)
            this.setCancelable(false)
            this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, v: View) {
        MakanYuk.getApp().setToken(loginResponse.access_token!!)
        val gsonUser = Gson()
        val jsonUser = gsonUser.toJson(loginResponse.user)
        MakanYuk.getApp().setUser(jsonUser)

        if (registerRequest.filePath == null) {
            Navigation.findNavController(v)
                .navigate(R.id.action_signUpAddressFragment_to_signUpSuccessFragment, null)

            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            Log.v(TAG, MakanYuk.getApp().getToken().toString())
            signUpPresenter.submitPhotoRegister(registerRequest.filePath!!, v)
        }
    }

    override fun onRegisterPhotoSuccess(v: View) {
        Navigation.findNavController(v)
            .navigate(R.id.action_signUpAddressFragment_to_signUpSuccessFragment, null)

        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }
}