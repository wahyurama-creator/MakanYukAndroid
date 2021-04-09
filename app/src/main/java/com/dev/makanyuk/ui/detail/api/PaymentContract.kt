package com.dev.makanyuk.ui.detail.api

import com.dev.makanyuk.base.BasePresenter
import com.dev.makanyuk.base.BaseView
import com.dev.makanyuk.model.response.checkout.CheckoutResponse

interface PaymentContract {
    interface View : BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(
            foodId: String,
            userId: String,
            quantity: String,
            total: String,
            status: String,
            view: android.view.View
        )
    }
}