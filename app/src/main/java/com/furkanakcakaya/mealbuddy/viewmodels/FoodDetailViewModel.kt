package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository


class FoodDetailViewModel(application: Application) : AndroidViewModel(application){
    private val fRepo = FoodRepository

    fun addToCart(cartObject: Cart) {
        if (cartObject.cartFoodId == "-1"){
            fRepo.addToCart(cartObject)
        }else{
            fRepo.updateCartItem(cartObject)
        }
    }
}