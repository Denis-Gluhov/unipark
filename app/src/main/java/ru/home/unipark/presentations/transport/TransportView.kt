package ru.home.unipark.presentations.transport

interface TransportView {
    fun onSetData(data: List<Transport>)

    fun onShowDialogRequestAppExit()

    fun onShowLoad()

    fun onHideLoad()

    fun onShowAuthScreen()

    fun onExitThisScreen()
}