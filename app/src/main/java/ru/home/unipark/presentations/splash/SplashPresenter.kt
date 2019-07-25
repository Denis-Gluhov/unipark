package ru.home.unipark.presentations.splash

class SplashPresenter (
    private val view: SplashView
) {

    fun start() {
        view.onShowAuthScreen()
        view.onExitThisScreen()
    }

}