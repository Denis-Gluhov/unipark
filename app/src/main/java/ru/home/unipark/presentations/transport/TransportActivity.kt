package ru.home.unipark.presentations.transport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_transport.*
import ru.home.unipark.R
import ru.home.unipark.presentations.auth.AuthActivity

class TransportActivity : AppCompatActivity(), TransportView {

    private lateinit var presenter: TransportPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport)
        presenter = TransportPresenter(this)
        initUi()
    }

    private fun initUi() {
        tbTransport.inflateMenu(R.menu.transport_menu_toolbar)
        tbTransport.setOnMenuItemClickListener {
            presenter.requestAppExit()
            true
        }
    }

    override fun onShowDialogRequestAppExit() {
        val requestDialog = AlertDialog.Builder(this)
            .setMessage("Вы действительно хотите выйти из учетной записи?")
            .setPositiveButton("Выйти") { _, _ -> presenter.appExit() }
            .setNegativeButton("Отмена", null)
        requestDialog.create().show()

    }

    override fun onShowLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHideLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowAuthScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
    }

    override fun onExitThisScreen() {
        this.finish()
    }
}