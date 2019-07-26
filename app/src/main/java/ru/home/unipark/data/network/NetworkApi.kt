package ru.home.unipark.data.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.home.unipark.data.network.model.request.AuthRequest
import ru.home.unipark.data.network.model.request.RegRequest
import ru.home.unipark.data.network.model.response.AuthData
import ru.home.unipark.data.network.model.response.QuitData
import ru.home.unipark.data.network.model.response.RegData
import ru.home.unipark.data.network.model.response.Response

interface NetworkApi {
    @POST("/v2/users/auth")
    fun auth(@Body request: AuthRequest): Single<Response<AuthData>>

    @POST("/v2/users/quit")
    fun quit(@Header("Authorization") token: String): Single<Response<QuitData>>

    @POST("/v2/users")
    fun reg(@Body request: RegRequest): Single<Response<RegData>>

    @GET("/v2/transports/filter?city_id=1&transport_type_id=18")
    fun getTransports(@Header("token") token: String)

    companion object Factory {
        private val BASE_URL = "https://testapi.unipark.kz/"

        fun create(): NetworkApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(initClient())
                .build()
            return retrofit.create(NetworkApi::class.java)
        }

        private fun initClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("api-version", "21")
                        .build()
                    chain.proceed(newRequest)
                }
            return builder.build()
        }
    }
}