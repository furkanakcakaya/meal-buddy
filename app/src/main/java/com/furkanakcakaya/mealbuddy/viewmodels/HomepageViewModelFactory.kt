package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class HomepageViewModelFactory(var application: Application) :ViewModelProvider.NewInstanceFactory() {
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return HomepageViewModel(application) as T
    }
}