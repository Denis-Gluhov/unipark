package ru.home.unipark.data.network.model.response

import com.google.gson.annotations.SerializedName

class QuitData(
    @SerializedName("phone_number")
    val phone: String,

    @SerializedName("city_name")
    val cityName: String,

    @SerializedName("firstname")
    val firstName: String
)