package com.dev.makanyuk.ui.auth.signin.api

import com.dev.makanyuk.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignInPresenter(private val v: SignInContract.View) : SignInContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun submitLogin(email: String, password: String) {
        v.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                v.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data.let { it1 -> v.onLoginSuccess(it1!!) }
                } else {
                    it.meta?.message?.let { it1 -> v.onLoginFailed(it1) }
                }
            }, {
                v.dismissLoading()
                v.onLoginFailed(it.message.toString())
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }

}