package com.victor.coffeeshop_kotlin.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.victor.coffeeshop_kotlin.database.dao.CoffeeShopDao
import com.victor.coffeeshop_kotlin.model.CoffeeShop


@Database(
    entities = [
        CoffeeShop::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCoffeeShopDao(): CoffeeShopDao

    companion object {

        fun getAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "pessoa_db")
                .allowMainThreadQueries()
                .build()
        }
    }

}