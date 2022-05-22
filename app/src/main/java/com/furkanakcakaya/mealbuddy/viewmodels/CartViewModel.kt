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

    fun removeCartItem(cartFoodId: String) {
        fRepo.removeFromCart(cartFoodId)
    }

    fun updateCartItem(cartItem: Cart){
        if (cartItem.foodQuantity.toInt() == 0 ){
            fRepo.removeFromCart(cartItem.cartFoodId)
        }else{
            fRepo.updateCartItem(
                cartItem.cartFoodId,
                cartItem.foodName,
                cartItem.foodPicture,
                cartItem.foodPrice,
                cartItem.foodQuantity,
                cartItem.orderUsername)
        }
    }
}