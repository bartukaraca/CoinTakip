package com.bartukaraca.cointakip

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(
            cryptoModel: CryptoModel,
            position: Int,
            listener: Listener
        ) {
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }


            itemView.text_name.text = "${cryptoModel.name}"
            itemView.text_price.text = "$"+ cryptoModel.quotes.USD.price.toString().removeRange(7,cryptoModel.quotes.USD.price.toString().length)
            itemView.text_symbol.text=cryptoModel.symbol

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position], position, listener)

    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}