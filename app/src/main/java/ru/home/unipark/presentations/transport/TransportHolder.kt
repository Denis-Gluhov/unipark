package ru.home.unipark.presentations.transport

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_transport.view.*

class TransportHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {

    fun bind(item: Transport) {
        view.tvTransportNameItem.text = item.name
        view.tvNumberTransportItem.text = item.transportNumber
    }

}