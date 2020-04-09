package com.victor.coffeeshop_kotlin.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.victor.coffeeshop_kotlin.model.domain.CoffeeShop

@Dao
interface CoffeeShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoffeeShopSuggestions(coffeeshops: List<CoffeeShop>)
}