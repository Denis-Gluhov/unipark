package ru.home.unipark.data.network.model.request

import com.google.gson.annotations.SerializedName

class AuthRequest(
    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("phone_type")
    val phoneType: Int = 1,

    @SerializedName("phone_token")
    val phoneToken: String? = null
)