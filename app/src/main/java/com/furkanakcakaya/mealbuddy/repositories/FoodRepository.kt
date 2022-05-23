package com.furkanakcakaya.mealbuddy.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.entities.CartResponse
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.entities.FoodResponse
import com.furkanakcakaya.mealbuddy.retrofit.ApiService
import com.furkanakcakaya.mealbuddy.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

object FoodRepository {
    private val TAG = "FoodRepository"
    private var allFoodItems: MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()
    private var currentCart: MutableLiveData<List<Cart>> = MutableLiveData<List<Cart>>()
    private var apiService: ApiService = ApiUtils.getApiService()

    init {
        fetchAllFoodItems()
        fetchCart("furkanakcakaya")
    }

    fun getFoods():LiveData<List<Food>>{
        return allFoodItems
    }

    fun getCart():LiveData<List<Cart>>{
        return currentCart
    }

    private fun fetchAllFoodItems() {
        apiService.getAllFoodItems().enqueue(object: Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                allFoodItems.value = response.body().foods
            }
            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {}
        })
    }

    private fun fetchCart(orderUsername: String) {
        apiService.getCartItems(orderUsername).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                currentCart.value = response.body().message.sortedBy { it.foodName }
            }
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun addToCart(cart: Cart){
        apiService.addFoodToCart(
            cart.foodName,
            cart.foodPicture,
            cart.foodPrice,
            cart.foodQuantity,
            cart.orderUsername
        ).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                fetchCart(cart.orderUsername)
            }
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun removeFromCart(cartFoodId: String, orderUsername: String){
        apiService.deleteCartItem(
            cartFoodId,
            orderUsername //TODO: Change this to orderUsername > SHARED PREFS
        ).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                fetchCart(orderUsername)
            }
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun updateCartItem(cart: Cart) {
        apiService.deleteCartItem(cart.cartFoodId,cart.orderUsername).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                apiService.addFoodToCart(
                    cart.foodName,
                    cart.foodPicture,
                    cart.foodPrice,
                    cart.foodQuantity,
                    cart.orderUsername
                ).enqueue(object: Callback<CartResponse> {
                    override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                        Log.i(TAG, "onResponse: updated")
                        fetchCart(cart.orderUsername)
                    }

                    override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
                })
            }
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun clearCart() {
        currentCart.value!!.forEach {
            apiService.deleteCartItem(it.cartFoodId,it.orderUsername).enqueue(object: Callback<CartResponse> {
                override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                    Log.i(TAG, "onResponse: deleted")
                }
                override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
            })
        }
    }
}