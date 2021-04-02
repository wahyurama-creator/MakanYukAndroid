package com.dev.makanyuk.ui.auth.signup.api

import android.net.Uri
import android.view.View
import androidx.annotation.NonNull
import com.dev.makanyuk.model.request.RegisterRequest
import com.dev.makanyuk.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpPresenter(private val view: SignUpContract.View) : SignUpContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun submitRegister(registerRequest: RegisterRequest, v: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.register(
            registerRequest.name!!,
            registerRequest.email!!,
            registerRequest.password!!,
            registerRequest.password_confirmation!!,
            registerRequest.address!!,
            registerRequest.city!!,
            registerRequest.houseNumber!!,
            registerRequest.phoneNumber!!,
            registerRequest.current_team_id,
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data.let { it1 -> view.onRegisterSuccess(it1!!, v) }
                } else {
                    it.meta?.message?.let { it1 -> view.onRegisterFailed(it1) }
                }
            }, {
                view.dismissLoading()
                view.onRegisterFailed(it.message.toString())
            })
        mCompositeDisposable.add(disposable)
    }

    override fun submitPhotoRegister(@NonNull filePath: Uri, v: View) {
        view.showLoading()
        val profileImage = File(filePath.path!!)
        val profileImageRequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), profileImage)
        val profileImageParams =
            MultipartBody.Part.createFormData("file", profileImage.name, profileImageRequestBody)

        val disposable = HttpClient.getInstance().getApi()!!.registerPhoto(
            profileImageParams
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data.let { _ -> view.onRegisterPhotoSuccess(v) }
                } else {
                    view.onRegisterFailed(it.meta?.message.toString())
                }
            }, {
                view.dismissLoading()
                view.onRegisterFailed(it.message.toString())
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}