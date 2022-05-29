package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class FoodDetailViewModelFactory(var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return FoodDetailViewModel(application) as T
    }
}