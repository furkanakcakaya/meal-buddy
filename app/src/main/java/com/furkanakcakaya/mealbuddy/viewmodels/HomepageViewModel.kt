package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furkanakcakaya.mealbuddy.entities.FoodItem
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository

class HomepageViewModel : ViewModel(){
    private val fRepo = FoodRepository()
    var foodList:LiveData<List<FoodItem>>

    init {
        fetchFoodList()
        foodList = fRepo.getItems()
    }

    private fun fetchFoodList() {
        fRepo.fetchFoodList()
    }

}