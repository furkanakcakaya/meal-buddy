package com.furkanakcakaya.mealbuddy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository

class CartViewModel : ViewModel() {
    private val TAG = "CartViewModel"
    private val fRepo = FoodRepository
    var currentCart: LiveData<List<Cart>> = fRepo.getCart()


}