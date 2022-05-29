package com.furkanakcakaya.mealbuddy.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanakcakaya.mealbuddy.preferences.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(var application: Application)  {
    private val ap = AppPreferences(application)
    private var username:MutableLiveData<String> = MutableLiveData<String>()
    private val fRepo = FoodRepository

    init {
        readUsername()
    }

    fun getUsername():LiveData<String>{
        return username
    }

    private fun readUsername(){
        CoroutineScope(Dispatchers.Main).launch {
            username.value = ap.readUsername()
        }
    }

    fun updateUsername(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            ap.saveUsername(username)
            readUsername()
            fRepo.setUsername(username)
        }
    }

}