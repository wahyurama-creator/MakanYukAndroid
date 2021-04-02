package com.dev.makanyuk.model.request

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(
    @Expose
    @SerializedName("name")
    var name: String? = null,
    @Expose
    @SerializedName("email")
    var email: String? = null,
    @Expose
    @SerializedName("password")
    var password: String? = null,
    @Expose
    @SerializedName("password_confirmation")
    var password_confirmation: String? = null,
    @Expose
    @SerializedName("address")
    var address: String? = null,
    @Expose
    @SerializedName("city")
    var city: String? = null,
    @Expose
    @SerializedName("houseNumber")
    var houseNumber: String? = null,
    @Expose
    @SerializedName("phoneNumber")
    var phoneNumber: String? = null,
    @Expose
    @SerializedName("current_team_id")
    var current_team_id: Int = 1,
    @Expose
    @SerializedName("filePath")
    var filePath: Uri? = null
) : Parcelable