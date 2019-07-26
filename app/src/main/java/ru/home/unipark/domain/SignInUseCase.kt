package ru.home.unipark.domain

import io.reactivex.Completable
import ru.home.unipark.data.prefs.PrefStorage

class SignInUseCase {

    private val pref = PrefStorage()

    fun execute(): Completable {
       return pref.read()
           .flatMapCompletable {
               Completable.complete()
           }
    }
}