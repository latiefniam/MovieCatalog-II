package com.example.moviecatalogbylatiefniam.utils

import androidx.test.espresso.idling.CountingIdlingResource

object Espressoldling {

    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)
    fun increment (){
        idlingResource.increment()
    }
    fun decrement(){
        idlingResource.decrement()
    }
}