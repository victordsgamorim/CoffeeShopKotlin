package com.victor.coffeeshop_kotlin.model.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys =
    [ForeignKey(
        entity = Coffee::class,
        parentColumns = ["id"],
        childColumns = ["coffeeId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val location: Geometry,
    val address: String,
    val coffeeId: String
) {

}