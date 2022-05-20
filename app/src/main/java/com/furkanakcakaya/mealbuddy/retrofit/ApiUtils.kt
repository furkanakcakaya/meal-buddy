package com.furkanakcakaya.mealbuddy.retrofit

class ApiUtils {
    companion object{
        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getApiService(): ApiService{
            return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
        }
    }
}