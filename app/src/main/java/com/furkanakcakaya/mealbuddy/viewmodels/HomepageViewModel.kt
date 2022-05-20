package com.furkanakcakaya.mealbuddy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository

class HomepageViewModel : ViewModel(){
    private val fRepo = FoodRepository()
    var foodList:LiveData<List<Food>>

    init {
        fetchFoodList()
        foodList = fRepo.getItems()
    }

    private fun fetchFoodList() {
        fRepo.fetchAllFoodItems()
    }

    fun addToCart(food: Food){
        fRepo.addToCart(food)
    }

    fun getCart() {
        fRepo.getFoodInCart()
    }

}