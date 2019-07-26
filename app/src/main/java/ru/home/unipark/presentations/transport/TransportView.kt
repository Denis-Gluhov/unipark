package ru.home.unipark.presentations.transport

interface TransportView {
    fun onShowDialogRequestAppExit()

    fun onShowLoad()

    fun onHideLoad()

    fun onShowAuthScreen()

    fun onExitThisScreen()
}