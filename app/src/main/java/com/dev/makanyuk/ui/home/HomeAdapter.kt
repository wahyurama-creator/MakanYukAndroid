package com.dev.makanyuk.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.makanyuk.R
import com.dev.makanyuk.model.dummy.HomeModel
import kotlinx.android.synthetic.main.item_home_horizontal.view.*

class HomeAdapter(
    private val listData: List<HomeModel>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, data: HomeModel)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: HomeModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tv_title.text = data.title
                rating_bar_food.rating = data.rating

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }
}