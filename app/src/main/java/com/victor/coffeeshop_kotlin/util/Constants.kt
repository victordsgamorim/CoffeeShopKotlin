package com.victor.coffeeshop_kotlin.util

const val RETROFIT_BASE_URL = "https://maps.googleapis.com/maps/api/"
const val PLACE_API_GET_TEST =
    "place/nearbysearch/json?location=53.3496055,-6.2601481&radius=50&types=cafe&name=coffeeshop&key=AIzaSyDyJqQzhSEuEiPk3A1jWBTJ4m9aaExb_k8"
const val COFFEE_SHOP_PLACES_DATABASE_NAME = "coffee_shop_db"
const val SHARED_PREFERENCES_NAME = "com.victor.coffeeshop_kotlin.LOCATION"
const val CURRENT_LOCATION_SHARED_PREF_KEY = "CURRENT_LOCATION"

object PlaceParameters {
    const val PLACE_RADIUS = 50f
    const val PLACE_TYPE = "cafe"
    const val PLACE_NAME = "coffeeshop"
    const val PLACE_CREDENTIAL_KEY = "AIzaSyDroYe1swTyZlMwKqdjANES6DNu04ZM3YE"
}


object ErrorMessage {

    const val CANCEL_JOB_UNKNOWN_ERROR = "Unknown Error"
    const val CANCEL_JOB_TIMEOUT = "Time Out"
}
