package ru.home.unipark.presentations.transport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_transport.*
import ru.home.unipark.R
import ru.home.unipark.presentations.auth.AuthActivity

class TransportActivity : AppCompatActivity(), TransportView {

    private lateinit var presenter: TransportPresenter
    private lateinit var adapter: TransportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport)
        presenter = TransportPresenter(this)
        initUi()
    }

    override fun onStart() {
        super.onStart()
        presenter.loadTransport()
    }

    private fun initUi() {
        tbTransport.inflateMenu(R.menu.transport_menu_toolbar)
        tbTransport.setOnMenuItemClickListener {
            presenter.requestAppExit()
            true
        }
        rlRefreshTransport.setOnRefreshListener {
            presenter.loadTransport()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = rvListTransport
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        adapter = TransportAdapter()
        recyclerView.adapter = adapter
    }

    override fun onShowDialogRequestAppExit() {
        val requestDialog = AlertDialog.Builder(this)
            .setMessage("Вы действительно хотите выйти из учетной записи?")
            .setPositiveButton("Выйти") { _, _ -> presenter.appExit() }
            .setNegativeButton("Отмена", null)
        requestDialog.create().show()

    }

    override fun onSetData(data: List<Transport>) {
        adapter.updateData(data)
    }

    override fun onShowLoad() {
        rlRefreshTransport.isRefreshing = true
    }

    override fun onHideLoad() {
        rlRefreshTransport.isRefreshing = false
    }

    override fun onShowAuthScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
    }

    override fun onExitThisScreen() {
        this.finish()
    }
}