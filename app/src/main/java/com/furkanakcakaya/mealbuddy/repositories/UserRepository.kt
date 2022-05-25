package com.furkanakcakaya.mealbuddy.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanakcakaya.mealbuddy.preferences.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val context: Context) {
    private val ap = AppPreferences(context)
    private lateinit var username:MutableLiveData<String>

    init {
        readUsername()
    }

    fun getUsername():LiveData<String>{
        return username
    }

    fun saveUsername(username:String){
        CoroutineScope(Dispatchers.Main).launch {
            ap.saveUsername(username)
        }
    }

    fun readUsername(){
        CoroutineScope(Dispatchers.Main).launch {
            username.value = ap.readUsername()
        }
    }

}