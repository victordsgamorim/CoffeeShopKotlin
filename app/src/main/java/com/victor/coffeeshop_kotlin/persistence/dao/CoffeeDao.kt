package com.victor.coffeeshop_kotlin.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.victor.coffeeshop_kotlin.model.domain.Coffee

@Dao
interface CoffeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShop(coffeeShop: Coffee)

    @Query("SELECT * FROM Coffee")
    suspend fun getList(): List<Coffee>

    @Query("SELECT * FROM Coffee WHERE id = :id")
    suspend fun getCoffee(id: String): Coffee?

}