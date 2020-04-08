package com.victor.coffeeshop_kotlin.util

const val RETROFIT_BASE_URL = "https://maps.googleapis.com/maps/api/"
const val PLACE_API_GET_TEST =
    "place/nearbysearch/json?location=53.3496055,-6.2601481&radius=50&types=cafe&name=coffeeshop&key=AIzaSyDyJqQzhSEuEiPk3A1jWBTJ4m9aaExb_k8"

object ErrorMessage {

    const val CANCEL_JOB_UNKNOWN_ERROR = "Unknown Error"
    const val CANCEL_JOB_TIMEOUT = "Time Out"
}
