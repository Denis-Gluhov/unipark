package ru.home.unipark.data.network.model.response

import com.google.gson.annotations.SerializedName

class Response<T>(
    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data: T,

    @SerializedName("message")
    val message: String
)