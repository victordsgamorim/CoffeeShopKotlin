package com.victor.coffeeshop_kotlin.model.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
data class Coffee(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val placeId: String,
    val name: String,
    val openNow: OpeningHours,
    val photo: List<Photos>,
    val priceLevel: Int,
    val rating: Double
)