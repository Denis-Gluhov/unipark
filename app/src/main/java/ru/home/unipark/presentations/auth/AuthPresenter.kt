package ru.home.unipark.presentations.auth

import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.home.unipark.domain.AuthUseCase
import ru.home.unipark.presentations.base.BasePresenter

class AuthPresenter (
    private val view: AuthView
): BasePresenter() {

    private val auth = AuthUseCase()

    fun auth(phone: String, password: String) {
        view.onHideErrorFields()
        when {
            TextUtils.isEmpty(phone) -> view.onShowErrorEmptyPhone()
            TextUtils.isEmpty(password) -> view.onShowErrorEmptyPassword()
            else -> {
                disposables += auth.execute(phone, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { view.onShowLoad() }
                    .doAfterTerminate { view.onHideLoad() }
                    .subscribe({
                        view.onShowTransportScreen()
                        view.onExitThisScreen()
                    }) { /****/ }
            }
        }
    }

    fun reg() {
        view.onShowRegScreen()
    }
}