package ru.home.unipark.presentations.auth

interface AuthView {
    fun onShowLoad()

    fun onHideLoad()

    fun onShowErrorEmptyPhone()

    fun onShowErrorEmptyPassword()

    fun onHideErrorFields()

    fun onShowRegScreen()

    fun onShowTransportScreen()

    fun onExitThisScreen()
}