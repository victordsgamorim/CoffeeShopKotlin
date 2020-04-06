package com.victor.coffeeshop_kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.model.CoffeeShop
import kotlinx.android.synthetic.main.cardview_items.view.*

class CoffeeShopMainScreenAdapter(
    private val context: Context,
    private val coffeeShop: MutableList<CoffeeShop> = mutableListOf<CoffeeShop>()
) :
    RecyclerView.Adapter<CoffeeShopMainScreenAdapter.CoffeeShopMainScreenViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoffeeShopMainScreenViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.cardview_items, parent, false)
        return CoffeeShopMainScreenViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return coffeeShop.size
    }

    override fun onBindViewHolder(holder: CoffeeShopMainScreenViewHolder, position: Int) {
        val coffee = coffeeShop[position]
        holder.bindInformation(coffee)
    }

    fun update(coffeeShop: List<CoffeeShop>) {
        notifyItemRangeRemoved(0, coffeeShop.size)
        this.coffeeShop.clear()
        this.coffeeShop.addAll(coffeeShop)
        notifyItemRangeInserted(0, coffeeShop.size)

    }

    class CoffeeShopMainScreenViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindInformation(coffee: CoffeeShop) {
            itemView.coffeeshop_cardview_item_title.text = coffee.name
            itemView.coffeeshop_cardview_score.text = coffee.rate.toString()
        }

    }
}