package com.dev.makanyuk.network

import com.dev.makanyuk.model.response.Wrapper
import com.dev.makanyuk.model.response.checkout.CheckoutResponse
import com.dev.makanyuk.model.response.home.HomeResponse
import com.dev.makanyuk.model.response.login.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("address") address: String,
        @Field("city") city: String,
        @Field("houseNumber") houseNumber: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("current_team_id") current_team_id: Int,
    ): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
        @Part profileImage: MultipartBody.Part
    ): Observable<Wrapper<Any>>

    @GET("food")
    fun getHome(): Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("food_id") foodId: String,
        @Field("user_id") userId: String,
        @Field("quantity") quantity: String,
        @Field("total") total: String,
        @Field("status") status: String,
    ): Observable<Wrapper<CheckoutResponse>>
}