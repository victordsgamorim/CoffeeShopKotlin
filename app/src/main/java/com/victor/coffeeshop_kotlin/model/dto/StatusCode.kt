package com.victor.coffeeshop_kotlin.model.dto

enum class StatusCode {
    OK,
    ZERO_RESULTS,
    OVER_QUERY_LIMIT,
    REQUEST_DENIED,
    INVALID_REQUEST,
    UNKNOWN_ERROR
}