package ru.home.unipark.data.network.model.request

import com.google.gson.annotations.SerializedName

class RegRequest(
    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("firstname")
    val firstName: String,

    @SerializedName("city_id")
    val cityId: Int,

    @SerializedName("is_driver")
    val isDriver: Int = 0
)