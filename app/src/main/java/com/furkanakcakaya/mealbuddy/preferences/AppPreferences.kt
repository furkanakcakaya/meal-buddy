package com.furkanakcakaya.mealbuddy.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPreferences(var context: Context) {
    companion object{
        private val Context.ds : DataStore<Preferences> by preferencesDataStore("preferences")
        val USERNAME_KEY = stringPreferencesKey("USERNAME")
    }

    suspend fun saveUsername(username:String){
        context.ds.edit {
            it[USERNAME_KEY] = username
        }
    }

    suspend fun readUsername(): String {
        val p = context.ds.data.first()
        return p[USERNAME_KEY] ?: "furkanakcakaya"
    }

}