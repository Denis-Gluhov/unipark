package ru.home.unipark.domain

import io.reactivex.Completable
import ru.home.unipark.data.exceptions.ServerException
import ru.home.unipark.data.network.NetworkApi
import ru.home.unipark.data.prefs.PrefStorage

class QuitUseCase {

    private val prefStorage = PrefStorage()
    private val api = NetworkApi.create()

    fun execute(): Completable {
        return prefStorage.read()
            .flatMap { api.quit("Bearer " + it.token) }
            .flatMapCompletable {
                if (it.status == 200) {
                    prefStorage.clear()
                } else {
                    Completable.error(ServerException(it.status, it.message))
                }
            }
    }
}