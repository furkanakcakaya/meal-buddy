package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository
import com.furkanakcakaya.mealbuddy.repositories.UserRepository

class HomepageViewModel(application: Application)  : AndroidViewModel(application){
    private val fRepo = FoodRepository
    private val uRepo = UserRepository(application)
    var foodList:LiveData<List<Food>> = fRepo.getFoods()

    fun searchFood(p0: String) {
        fRepo.searchFood(p0)
        uRepo.getUsername().value?.let { fRepo.setUsername(it) }
    }

    fun checkIfFoodIsInCart(food: Food): Boolean {
        return fRepo.checkIfFoodIsInCart(food)
    }

    fun getCartItem(name: String): Cart {
        return fRepo.getCartItem(name)
    }

    fun getUsername(): String {
        return uRepo.getUsername().value!!
    }
}