package com.furkanakcakaya.mealbuddy.retrofit

import com.furkanakcakaya.mealbuddy.entities.CartResponse
import com.furkanakcakaya.mealbuddy.entities.FoodResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFoodItems(): Call<FoodResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addFoodToCart(
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi")  foodImage: String,
        @Field("yemek_fiyat") foodPrice: String,
        @Field("yemek_siparis_adet") foodQuantity: String,
        @Field("kullanici_adi") orderUsername: String
    ): Call<CartResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getCartItems(@Field("kullanici_adi") orderUsername: String): Call<CartResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteCartItem(@Field("sepet_yemek_id") foodId: String, @Field("kullanici_adi") orderUsername: String): Call<CartResponse>




}