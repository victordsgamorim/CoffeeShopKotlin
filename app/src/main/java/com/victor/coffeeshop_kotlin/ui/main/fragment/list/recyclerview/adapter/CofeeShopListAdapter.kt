package com.victor.coffeeshop_kotlin.ui.main.fragment.list.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victor.coffeeshop_kotlin.databinding.CardviewItemsBinding
import com.victor.coffeeshop_kotlin.model.domain.Coffee

class CoffeeShopListAdapter(var onItemClick: (Coffee) -> Unit = {}) :
    ListAdapter<Coffee, CoffeeShopListAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = CardviewItemsBinding.inflate(inflater, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coffee = getItem(position).let { it }
            ?: throw NullPointerException("CoffeeShopListAdapter: Coffee object is null")

        holder.bindInformation(coffee)
    }

    inner class ViewHolder(private val binding: CardviewItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
            binding.coffeeShop = coffee
        }


    }

}

object DiffUtilCallback : DiffUtil.ItemCallback<Coffee>() {
    override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee) = oldItem == newItem
}


