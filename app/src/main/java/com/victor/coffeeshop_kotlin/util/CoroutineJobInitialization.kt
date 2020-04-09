package com.victor.coffeeshop_kotlin.util

import kotlinx.coroutines.Job

interface CoroutineJobInitialization {

    fun initCoroutineJob(): Job
}