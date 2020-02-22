package com.victor.coffeeshop_kotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.victor.coffeeshop_kotlin.model.CoffeeShop

@Dao
interface CoffeeShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(shop: CoffeeShop)
}