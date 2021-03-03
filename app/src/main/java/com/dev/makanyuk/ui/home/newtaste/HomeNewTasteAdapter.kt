package com.dev.makanyuk.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.makanyuk.R
import com.dev.makanyuk.model.dummy.HomeVerticalModel
import com.dev.makanyuk.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomeNewTasteAdapter(
    private val listData: List<HomeVerticalModel>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeNewTasteAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewTasteAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, data: HomeVerticalModel)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: HomeVerticalModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tv_title.text = data.title
                tv_subtitle.formatPrice(data.price)
                rating_bar_food.rating = data.rating

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }
}