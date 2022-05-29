package com.furkanakcakaya.mealbuddy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.furkanakcakaya.mealbuddy.repositories.FoodRepository
import com.furkanakcakaya.mealbuddy.repositories.UserRepository

class AccountViewModel(application: Application):AndroidViewModel(application) {
    private val uRepo = UserRepository(application)
    private val fRepo = FoodRepository
    val username:LiveData<String> = uRepo.getUsername()

    fun updateUsername(username:String){
        uRepo.updateUsername(username)
    }

}