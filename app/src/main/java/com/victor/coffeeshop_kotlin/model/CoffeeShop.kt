package com.victor.coffeeshop_kotlin.model

class CoffeeShop(
    val name: String,
    val addresses: List<CoffeeAddress>,
    val information: String,
    val menu: List<CoffeeOffer>,
    val openHour: List<OpenHour>,
    val image: List<CoffeeImage>,
    val socialMedia: List<SocialMedia>,
    val rate: Double,
    val contact: List<Contact>
)