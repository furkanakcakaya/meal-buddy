package com.furkanakcakaya.mealbuddy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository

class CartViewModel : ViewModel() {
    private val TAG = "cartViewModel"
    private val fRepo = FoodRepository
    var currentCart: LiveData<List<Cart>> = fRepo.getCart()

    fun removeCartItem(cartFoodId: String, orderUsername: String) {
        fRepo.removeFromCart(cartFoodId, orderUsername)
    }

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