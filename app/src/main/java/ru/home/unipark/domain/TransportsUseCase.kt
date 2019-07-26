package ru.home.unipark.domain

import io.reactivex.Single
import ru.home.unipark.data.exceptions.ServerException
import ru.home.unipark.data.network.NetworkApi
import ru.home.unipark.data.network.model.request.TransportRequest
import ru.home.unipark.data.prefs.PrefStorage

class TransportsUseCase {

    private val prefStorage = PrefStorage()
    private val api = NetworkApi.create()

    fun execute(): Single<List<Params>> {
        return prefStorage.read()
            .flatMap {
                val request = TransportRequest(1, 18)
                api.getTransports("Bearer " + it.token, request)
            }
            .flatMap {
                if (it.status == 200) { Single.just(it.data) }
                else { Single.error(ServerException(it.status, it.message)) }
            }
            .toObservable()
            .flatMapIterable { it }
            .map { return@map Params(it.price, it.name, it.driverName, it.transportNumber) }
            .toList()
    }

    class Params(
        val price: Int?,
        val name: String,
        val driverName: String,
        val transportNumber: String
    )
}