package com.victor.coffeeshop_kotlin.ui.splashscreen.state

import com.victor.coffeeshop_kotlin.model.dto.GooglePlaceDto

class SplashScreenViewState(
    var result: GooglePlaceDto? = null,
    var sharedPrefStatus: Boolean = false
)