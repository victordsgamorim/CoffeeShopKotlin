package com.victor.coffeeshop_kotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victor.coffeeshop_kotlin.R

private class CoffeeShopMainScreenAdapter(private val context: Context) :
    RecyclerView.Adapter<CoffeeShopMainScreenAdapter.CoffeeShopMainScreenViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoffeeShopMainScreenViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.coffeeshop_items_cardview, parent, false)
        return CoffeeShopMainScreenViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CoffeeShopMainScreenViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class CoffeeShopMainScreenViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }
}