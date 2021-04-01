package com.dev.makanyuk.model.response.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("access_token")
    val access_token: String? = null,
    @Expose
    @SerializedName("token_type")
    val token_type: String? = null,
    @Expose
    @SerializedName("user")
    val user: User? = null
)