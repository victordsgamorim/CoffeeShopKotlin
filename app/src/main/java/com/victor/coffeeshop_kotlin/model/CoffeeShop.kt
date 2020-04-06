package com.victor.coffeeshop_kotlin.model

class CoffeeShop(
    val id: Int,
    val name: String,
    val addresses: List<String>,
    val information: String,
    val menu: List<CoffeeOffer>,
    val openHour: List<OpenHour>,
    val image: List<String>,
    val socialMedia: List<String>,
    val rate: Double,
    val contact: List<String>
)

class CoffeeOffer(
    private val name: String,
    private val price: String,
    private val image: String
)


class OpenHour(
    private val week: List<String>,
    private val hour: List<String>
)
