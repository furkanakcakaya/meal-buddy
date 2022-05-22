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
        fetchCart()
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

    private fun fetchCart() {
        apiService.getCartItems("furkanakcakaya").enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                currentCart.value = response.body().message.sortedBy { it.foodName }
            }

            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun addToCart(
        foodName: String,
        foodPicName: String,
        foodPrice: String,
        foodQuantity: String,
        username: String
    ){
        apiService.addFoodToCart(
            foodName,
            foodPicName,
            foodPrice,
            foodQuantity,
            username
        ).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                fetchCart()
            }

            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun removeFromCart(cartFoodId: String){
        apiService.deleteCartItem(
            cartFoodId,
            "furkanakcakaya" //TODO: Change this to orderUsername > SHARED PREFS
        ).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                fetchCart()
            }
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun updateCartItem(
        cartFoodId: String,
        foodName: String,
        foodPicName: String,
        foodPrice: String,
        foodQuantity: String,
        username: String) {
        apiService.deleteCartItem(cartFoodId,username).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                apiService.addFoodToCart(
                    foodName,
                    foodPicName,
                    foodPrice,
                    foodQuantity,
                    username
                ).enqueue(object: Callback<CartResponse> {
                    override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                        Log.i(TAG, "onResponse: updated")
                        fetchCart()
                    }

                    override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
                })
            }
            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })


    }

    fun removeCartItem(cart: Cart) {

    }


}