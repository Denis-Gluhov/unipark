package ru.home.unipark.domain

import io.reactivex.Completable
import ru.home.unipark.data.network.NetworkApi
import ru.home.unipark.data.network.model.request.AuthRequest
import ru.home.unipark.data.exceptions.ServerException
import ru.home.unipark.data.prefs.PrefStorage

class AuthUseCase {

    private val api = NetworkApi.create()
    private val pref = PrefStorage()

    fun execute(phone: String, password: String): Completable {
        val request = AuthRequest(phone, password, phoneType = 1, phoneToken = null)
        return api.auth(request)
            .flatMapCompletable {
                if (it.status == 200) {
                    val ph = it.data.phoneNumber
                    val token = it.data.accessToken
                    val firstName = it.data.firstName
                    val cityName = it.data.cityName
                    val cityId = it.data.cityId
                    pref.write(PrefStorage.Params(ph, token, firstName, cityName, cityId))
                } else {
                    val code = it.status
                    val message = it.message
                    Completable.error(ServerException(code, message))
                }
            }
    }
}