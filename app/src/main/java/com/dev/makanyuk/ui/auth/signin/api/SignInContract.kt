package com.dev.makanyuk.ui.auth.signin.api

import com.dev.makanyuk.base.BasePresenter
import com.dev.makanyuk.base.BaseView
import com.dev.makanyuk.model.response.login.LoginResponse

interface SignInContract {
    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SignInContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}