package com.furkanakcakaya.mealbuddy.entities

import java.io.Serializable

data class Food(
    var id: Int,
    var name: String,
    var picName: String,
    var price: Int
) : Serializable
