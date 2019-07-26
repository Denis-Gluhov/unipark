package ru.home.unipark.presentations.splash

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.home.unipark.domain.SignInUseCase
import ru.home.unipark.presentations.base.BasePresenter

class SplashPresenter (
    private val view: SplashView
): BasePresenter() {

    private val signIn = SignInUseCase()

    fun start() {
        disposables += signIn.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onShowTransportScreen()
                view.onExitThisScreen()
            }) {
                view.onShowAuthScreen()
                view.onExitThisScreen()
            }
    }

}