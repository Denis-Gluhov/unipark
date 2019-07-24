package ru.home.unipark.data.network.model.response

import com.google.gson.annotations.SerializedName

class AuthData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("firstname")
    val firstName: String,

    @SerializedName("city_name")
    val cityName: String,

    @SerializedName("city_id")
    val cityId: Int
)