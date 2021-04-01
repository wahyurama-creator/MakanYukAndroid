package com.dev.makanyuk.network

import com.dev.makanyuk.model.response.Wrapper
import com.dev.makanyuk.model.response.login.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Endpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>
}