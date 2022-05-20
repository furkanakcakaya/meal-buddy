package com.furkanakcakaya.mealbuddy.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanakcakaya.mealbuddy.entities.CartResponse
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.entities.FoodResponse
import com.furkanakcakaya.mealbuddy.retrofit.ApiService
import com.furkanakcakaya.mealbuddy.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class FoodRepository {
    private val TAG = "FoodRepository"
    private var allFoodItems: MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()
    private var apiService: ApiService = ApiUtils.getApiService()

    fun getItems():LiveData<List<Food>>{
        return allFoodItems
    }

    fun fetchAllFoodItems() {
        apiService.getAllFoodItems().enqueue(object: Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                allFoodItems.value = response.body().foods
            }
            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {}
        })
    }

    fun addToCart(food: Food){
        apiService.addFoodToCart(
            food.name,
            food.picName,
            food.price,
            "1",
            "furkanakcakaya" //TODO: Change this to orderUsername > SHARED PREFS
        ).enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                Log.i(TAG, "onResponse: " + response?.body()?.message)
            }

            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }

    fun getFoodInCart() {
        apiService.getCartItems("furkanakcakaya").enqueue(object: Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                response.body().message.forEach {
                    Log.i(TAG, "onResponse: $it")
                }
            }

            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {}
        })
    }


}