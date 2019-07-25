package ru.home.unipark.presentations.auth

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import ru.home.unipark.R
import ru.home.unipark.presentations.reg.RegActivity
import ru.home.unipark.presentations.transport.TransportActivity

class AuthActivity : AppCompatActivity(), AuthView {

    private lateinit var presenter: AuthPresenter
    private lateinit var pbLoadAuth: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        presenter = AuthPresenter(this)
        initUi()
    }

    private fun initUi() {
        btnSignInAuth.setOnClickListener { presenter.auth(etPhoneAuth.text.toString(),
            etPasswordAuth.text.toString()) }
        tvRegAuth.setOnClickListener { presenter.reg() }
    }

    override fun onShowLoad() {
        pbLoadAuth.visibility = VISIBLE
    }

    override fun onHideLoad() {
        pbLoadAuth.visibility = GONE
    }

    override fun onShowErrorEmptyPhone() {
        ilPhoneAuth.isErrorEnabled = true
        ilPhoneAuth.error = "Поле не должно быть пустым"
        etPhoneAuth.requestFocus()
    }

    override fun onShowErrorEmptyPassword() {
        ilPasswordAuth.isErrorEnabled = true
        ilPasswordAuth.error = "Поле не должно быть пустым"
        etPasswordAuth.requestFocus()
    }

    override fun onHideErrorFields() {
        ilPhoneAuth.isErrorEnabled = false
        ilPasswordAuth.isErrorEnabled = false
    }

    override fun onShowRegScreen() {
        startActivity(Intent(this, RegActivity::class.java))
    }

    override fun onShowTransportScreen() {
        startActivity(Intent(this, TransportActivity::class.java))
    }

    override fun onExitThisScreen() {
        this.finish()
    }
}