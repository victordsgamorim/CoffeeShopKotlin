package com.victor.coffeeshop_kotlin.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.victor.coffeeshop_kotlin.model.domain.Geometry
import com.victor.coffeeshop_kotlin.model.domain.OpeningHours
import com.victor.coffeeshop_kotlin.model.domain.Photos
import java.util.*


class GeometryConverter {

    @TypeConverter
    fun geometryToString(coffee: Geometry): String {
        return Gson().toJson(coffee)
    }

    @TypeConverter
    fun stringToGeometry(data: String): Geometry {
        return Gson().fromJson(data, Geometry::class.java)
    }

}

class OpenHoursConverter {
    @TypeConverter
    fun openingHoursToString(hours: OpeningHours): String {
        return Gson().toJson(hours)
    }

    @TypeConverter
    fun stringToOpeningHours(data: String): OpeningHours {
        return Gson().fromJson(data, OpeningHours::class.java)
    }
}

class PhotoConverter {
    @TypeConverter
    fun photosToString(photos: List<Photos>): String {
        return Gson().toJson(photos)
    }

    @TypeConverter
    fun stringToPhotos(data: String?): List<Photos> {
        if (data == null) {
            return Collections.emptyList();
        }
        val listType = object : TypeToken<List<Photos>>() {}.type
        return Gson().fromJson(data, listType);
    }
}

