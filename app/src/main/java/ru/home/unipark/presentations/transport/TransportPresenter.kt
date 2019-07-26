package ru.home.unipark.presentations.transport

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.home.unipark.domain.QuitUseCase
import ru.home.unipark.domain.TransportsUseCase
import ru.home.unipark.presentations.base.BasePresenter

class TransportPresenter(
    private val view: TransportView
): BasePresenter() {

    private val transport = TransportsUseCase()
    private val quit = QuitUseCase()

    fun requestAppExit() {
        view.onShowDialogRequestAppExit()
    }

    fun appExit() {
        disposables += quit.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onShowAuthScreen()
                view.onExitThisScreen()
            }) {}
    }

}