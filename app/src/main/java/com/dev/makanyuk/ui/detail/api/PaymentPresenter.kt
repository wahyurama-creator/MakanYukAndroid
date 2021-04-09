package com.dev.makanyuk.ui.detail.api

import android.view.View
import com.dev.makanyuk.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter(private val v: PaymentContract.View) : PaymentContract.Presenter {

    companion object {
        const val DEFAULT_STATUS = "ON_DELIVERY"
    }

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getCheckout(
        foodId: String,
        userId: String,
        quantity: String,
        total: String,
        view: View
    ) {
        v.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!
            .checkout(foodId, userId, quantity, total, DEFAULT_STATUS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                v.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> v.onCheckoutSuccess(it1, view) }
                } else {
                    it.meta?.message?.let { it1 -> v.onCheckoutFailed(it1) }
                }
            }, {
                v.dismissLoading()
                v.onCheckoutFailed(it.message.toString())
            })
        mCompositeDisposable.add(disposable!!)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}