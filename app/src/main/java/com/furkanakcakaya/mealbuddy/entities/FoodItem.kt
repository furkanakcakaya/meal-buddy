package com.furkanakcakaya.mealbuddy.entities

data class FoodItem(
    var id: Int,
    var name: String,
    var picName: String,
    var price: Int,
    var oldPrice: Int
)
