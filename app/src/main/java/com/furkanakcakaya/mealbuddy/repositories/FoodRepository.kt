package com.furkanakcakaya.mealbuddy.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanakcakaya.mealbuddy.entities.FoodItem

class FoodRepository {
    private val TAG = "FoodRepository"
    private var foodItems: MutableLiveData<List<FoodItem>> = MutableLiveData<List<FoodItem>>()

    fun getItems():LiveData<List<FoodItem>>{
        return foodItems
    }

    fun fetchFoodList() {
        val list : List<FoodItem> = listOf(
            FoodItem(1,"Ayran", "ayran.png", 45, 65),
            FoodItem(2,"Kola", "kola.png", 45, 65),
            FoodItem(3,"Kahve", "kahve.png", 45, 65),
            FoodItem(4,"Kola", "kola.png", 45, 65),
            FoodItem(5,"Kahve", "kahve.png", 45, 65),
            FoodItem(6,"Kola", "kola.png", 45, 65),
        )
        foodItems.value = list
    }


}