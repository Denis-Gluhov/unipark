package ru.home.unipark.data.network.model.response

import com.google.gson.annotations.SerializedName

class TransportData(
    @SerializedName("price")
    val price: Int?,

    @SerializedName("name")
    val name: String,

    @SerializedName("driver_name")
    val driverName: String,

    @SerializedName("transport_number")
    val transportNumber: String
)