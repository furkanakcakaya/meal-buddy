package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class CartViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(application) as T
    }
}