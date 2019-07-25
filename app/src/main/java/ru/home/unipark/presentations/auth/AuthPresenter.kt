package ru.home.unipark.presentations.auth

import android.text.TextUtils

class AuthPresenter (
    private val view: AuthView
) {

    fun auth(phone: String, password: String) {
        view.onHideErrorFields()
        when {
            TextUtils.isEmpty(phone) -> view.onShowErrorEmptyPhone()
            TextUtils.isEmpty(password) -> view.onShowErrorEmptyPassword()
            else -> {
                view.onShowTransportScreen()
                view.onExitThisScreen()
            }
        }
    }

    fun reg() {
        view.onShowRegScreen()
    }

}