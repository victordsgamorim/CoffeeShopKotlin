package com.victor.coffeeshop_kotlin.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class CoffeeShop(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("place_id")
    @Expose
    val place_id: String,

    @SerializedName("geometry")
    @Expose
    val geometry: Geometry,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("opening_hours")
    @Expose
    val opening_hours: OpeningHours,

    @SerializedName("photos")
    @Expose
    val photos: List<Photos>,

    @SerializedName("price_level")
    @Expose
    val price_level: Int,

    @SerializedName("rating")
    @Expose
    val rating: Double,

    @SerializedName("vicinity")
    @Expose
    val address: String
) {
    override fun toString(): String {
        return "CoffeeShop(id='$id', place_id='$place_id', geometry=$geometry, name='$name', opening_hours=$opening_hours, photos=$photos, price_level=$price_level, rating=$rating, address='$address')"
    }
}

class Geometry(
    @SerializedName("location")
    @Expose
    val location: Location
)

class Location(

    @SerializedName("lat")
    @Expose
    val latitude: Float,

    @SerializedName("lng")
    @Expose
    val longitude: Float
)

class OpeningHours(
    @SerializedName("open_now")
    @Expose
    val open_now: Boolean
)

class Photos(
    @SerializedName("width")
    @Expose
    val width: Long,

    @SerializedName("height")
    @Expose
    val height: Long,

    @SerializedName("photo_reference")
    @Expose
    val photo_reference: String
)







