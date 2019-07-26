package ru.home.unipark.data.network.model.request

import com.google.gson.annotations.SerializedName

class TransportRequest(
    @SerializedName("city_id")
    val cityId: Int,

    @SerializedName("transport_type_id")
    val transportTypeId: Int
)