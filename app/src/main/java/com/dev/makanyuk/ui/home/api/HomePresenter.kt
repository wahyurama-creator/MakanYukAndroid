package com.dev.makanyuk.ui.home.api

import com.dev.makanyuk.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val v: HomeContract.View) : HomeContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getHome() {
        v.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.getHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                v.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data.let { it1 -> v.onHomeSuccess(it1!!) }
                } else {
                    it.meta?.message.let { it1 -> v.onHomeFailed(it1!!) }
                }
            }, {
                v.dismissLoading()
                v.onHomeFailed(it.message.toString())
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }

}