package ru.home.unipark.presentations.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.home.unipark.R
import ru.home.unipark.presentations.auth.AuthActivity
import ru.home.unipark.presentations.transport.TransportActivity

class SplashActivity : AppCompatActivity(), SplashView {

    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
        presenter.start()
    }

    override fun onShowAuthScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
    }

    override fun onShowTransportScreen() {
        startActivity(Intent(this, TransportActivity::class.java))
    }

    override fun onExitThisScreen() {
        this.finish()
    }
}