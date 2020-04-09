package com.victor.coffeeshop_kotlin.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.victor.coffeeshop_kotlin.model.domain.Address
import com.victor.coffeeshop_kotlin.model.domain.Coffee
import com.victor.coffeeshop_kotlin.model.domain.CoffeeShop
import com.victor.coffeeshop_kotlin.persistence.converter.GeometryConverter
import com.victor.coffeeshop_kotlin.persistence.converter.OpenHoursConverter
import com.victor.coffeeshop_kotlin.persistence.converter.PhotoConverter
import com.victor.coffeeshop_kotlin.persistence.dao.AddressDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeShopDao

@Database(
    entities = [CoffeeShop::class, Coffee::class, Address::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GeometryConverter::class, OpenHoursConverter::class, PhotoConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCoffeeShopDao(): CoffeeShopDao
    abstract fun getCoffeeDao(): CoffeeDao
    abstract fun getAddressDao(): AddressDao

}