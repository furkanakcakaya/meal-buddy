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
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object FoodRepository {
    private const val TAG = "FoodRepository"
    private var allFoodItems: MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()
    private var currentCart: MutableLiveData<List<Cart>> = MutableLiveData<List<Cart>>()
    private var apiService: ApiService = ApiUtils.getApiService()
    private var username:String = "furkanakcakaya"

    init {
        fetchAllFoodItems()
        fetchCart(username)
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
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {Log.d(TAG, "onFailure: ${t?.message}")
                //Means that the cart is empty
                currentCart.value = listOf(
                    Cart(
                        foodName = "Cart is empty",
                        foodPrice = "0",
                        foodQuantity = "0",
                        foodPicture = "",
                        cartFoodId = "",
                        orderUsername = "default"
                    )
                )
            }
        })
    }

    fun addToCart(cart: Cart){
        val condition = currentCart.value?.find { it.foodName == cart.foodName }
        if(condition == null){
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
    }

    fun removeFromCart(cartFoodId: String, orderUsername: String){
        apiService.deleteCartItem(
            cartFoodId,
            orderUsername
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
        //TODO: Clear cart doesnt work properly. debug it
        currentCart.value!!.forEach {
            apiService.deleteCartItem(it.cartFoodId,it.orderUsername).enqueue(object: Callback<CartResponse> {
                override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                    Log.i(TAG, "onResponse: Cart Cleared.")
                }
                override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
            })
        }
        fetchCart(username)
    }

    fun getUsername(): String {
        return username
    }

    fun searchFood(query: String) {
        apiService.getAllFoodItems().enqueue(object: Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                val foods = response.body().foods
                allFoodItems.value = foods.filter { it.name.contains(query,ignoreCase = true) }
            }
            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {}
        })
    }

    fun checkIfFoodIsInCart(food: Food): Boolean {
        return currentCart.value!!.any { it.foodName == food.name }
    }

    fun getCartItem(name: String): Cart {
        return currentCart.value!!.find { it.foodName == name }!!
    }
}