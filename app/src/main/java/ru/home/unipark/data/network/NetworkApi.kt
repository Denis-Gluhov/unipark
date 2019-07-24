package ru.home.unipark.data.network

import io.reactivex.Single
import retrofit2.http.*
import ru.home.unipark.data.network.model.request.AuthRequest
import ru.home.unipark.data.network.model.request.RegRequest
import ru.home.unipark.data.network.model.response.AuthData
import ru.home.unipark.data.network.model.response.RegData
import ru.home.unipark.data.network.model.response.Response

interface NetworkApi {
    @POST("/v2/users/auth")
    fun auth(@Body request: AuthRequest): Single<Response<AuthData>>

    @POST("/v2/users/quit")
    fun quit(@Header("token") token: String): Compiler

    @POST("/v2/users")
    fun reg(@Body request: RegRequest): Single<Response<RegData>>

    @GET("/v2/transports/filter?city_id=1&transport_type_id=18")
    fun getTransports(@Header("token") token: String)
}