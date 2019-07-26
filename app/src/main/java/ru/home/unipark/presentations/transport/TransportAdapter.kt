package ru.home.unipark.presentations.transport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.home.unipark.R

class TransportAdapter: RecyclerView.Adapter<TransportHolder>() {

    private val data: MutableList<Transport> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportHolder {
        return TransportHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transport, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TransportHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateData(data: List<Transport>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}