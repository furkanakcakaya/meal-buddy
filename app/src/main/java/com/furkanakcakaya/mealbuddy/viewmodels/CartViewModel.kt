package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "cartViewModel"
    private val fRepo = FoodRepository
    var currentCart: LiveData<List<Cart>> = fRepo.getCart()

    fun removeCartItem(cartFoodId: String, orderUsername: String) {
        fRepo.removeFromCart(cartFoodId, orderUsername)
    }

    @Synchronized
    fun updateCartItem(cartItem: Cart){
        fRepo.updateCartItem(cartItem)
    }

    fun getUsername(): String {
        return fRepo.getUsername()
    }

    fun clearCart() {
        fRepo.clearCart()
    }
}