package com.furkanakcakaya.mealbuddy.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanakcakaya.mealbuddy.entities.Food

class FoodRepository {
    private val TAG = "FoodRepository"
    private var foodItems: MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()

    fun getItems():LiveData<List<Food>>{
        return foodItems
    }

    fun fetchFoodList() {
        val list : List<Food> = listOf(
            Food(1,"Ayran", "ayran.png", 45),
            Food(2,"Kola", "kola.png", 45),
            Food(3,"Kahve", "kahve.png", 45),
            Food(4,"Kola", "kola.png", 45),
            Food(5,"Kahve", "kahve.png", 45),
            Food(6,"Kola", "kola.png", 45),
        )
        foodItems.value = list
    }


}