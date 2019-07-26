package ru.home.unipark.data.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import ru.home.unipark.data.network.model.request.AuthRequest
import ru.home.unipark.data.network.model.request.RegRequest
import ru.home.unipark.data.network.model.request.TransportRequest
import ru.home.unipark.data.network.model.response.*

interface NetworkApi {
    @POST("/v2/users/auth")
    fun auth(@Body request: AuthRequest): Single<Response<AuthData>>

    @POST("/v2/users/quit")
    fun quit(@Header("Authorization") token: String): Single<Response<QuitData>>

    @POST("/v2/users")
    fun reg(@Body request: RegRequest): Single<Response<RegData>>

    @POST("/v2/transports/filter")
    fun getTransports(@Header("Authorization") token: String,
                      @Body request: TransportRequest): Single<Response<List<TransportData>>>

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