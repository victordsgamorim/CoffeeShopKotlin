package com.victor.coffeeshop_kotlin.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.victor.coffeeshop_kotlin.model.domain.Coffee

@Dao
interface CoffeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShop(coffeeShop: Coffee)
}