package com.dev.makanyuk.ui.home.api

import com.dev.makanyuk.base.BasePresenter
import com.dev.makanyuk.base.BaseView
import com.dev.makanyuk.model.response.home.HomeResponse

interface HomeContract {

    interface View : BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}