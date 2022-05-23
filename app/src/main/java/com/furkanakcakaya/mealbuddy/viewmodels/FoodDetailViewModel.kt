package com.furkanakcakaya.mealbuddy.viewmodels

import android.R
import android.util.Log
import androidx.lifecycle.ViewModel
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository


class FoodDetailViewModel : ViewModel() {
    private val TAG = "FoodDetailViewModel"
    private val fRepo = FoodRepository

    fun addToCart(cartObject: Cart) {
        if (cartObject.cartFoodId == "-1"){
            Log.d(TAG, "addToCart: Food Id is -1")
            fRepo.addToCart(cartObject)
        }else{
            fRepo.updateCartItem(cartObject)
        }
    }
}