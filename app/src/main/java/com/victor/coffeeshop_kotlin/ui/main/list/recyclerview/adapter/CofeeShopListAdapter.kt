package com.victor.coffeeshop_kotlin.ui.main.list.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.model.domain.Coffee
import kotlinx.android.synthetic.main.cardview_items.view.*

class CoffeeShopListAdapter(var onItemClick: (Coffee) -> Unit = {}) :
    ListAdapter<Coffee, CoffeeShopListAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cardview_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coffee = getItem(position).let { it }
            ?: throw NullPointerException("CoffeeShopListAdapter: Coffee object is null")

        holder.bindInformation(coffee)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var coffee: Coffee

        init {
            itemView.setOnClickListener {
                if (::coffee.isInitialized) {
                    onItemClick(coffee)
                    Log.i("Coffee name:", coffee.name)
                }
            }
        }

        fun bindInformation(coffee: Coffee) {
            this.coffee = coffee
            itemView.coffeeshop_cardview_item_title.text = coffee.name
            itemView.coffeeshop_cardview_score.text = coffee.rating.toString()
        }


    }

}

object DiffUtilCallback : DiffUtil.ItemCallback<Coffee>() {
    override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee) = oldItem == newItem
}


