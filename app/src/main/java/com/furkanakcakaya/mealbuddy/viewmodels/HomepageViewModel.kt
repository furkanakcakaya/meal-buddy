package com.furkanakcakaya.mealbuddy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository

class HomepageViewModel : ViewModel(){

    private val TAG = "HomepageViewModel"
    private val fRepo = FoodRepository
    var foodList:LiveData<List<Food>> = fRepo.getFoods()

    fun searchFood(p0: String) {
        fRepo.searchFood(p0)
    }

    fun checkIfFoodIsInCart(food: Food): Boolean {
        return fRepo.checkIfFoodIsInCart(food)
    }

    fun getCartItem(name: String): Cart {
        return fRepo.getCartItem(name)
    }
}