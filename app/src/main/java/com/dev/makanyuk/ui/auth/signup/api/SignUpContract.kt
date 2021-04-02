package com.dev.makanyuk.ui.auth.signup.api

import android.net.Uri
import com.dev.makanyuk.base.BasePresenter
import com.dev.makanyuk.base.BaseView
import com.dev.makanyuk.model.request.RegisterRequest
import com.dev.makanyuk.model.response.login.LoginResponse

interface SignUpContract {
    interface View : BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, v: android.view.View)
        fun onRegisterPhotoSuccess(v: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, v: android.view.View)
        fun submitPhotoRegister(filePath: Uri, v: android.view.View)
    }
}