package com.furkanakcakaya.mealbuddy.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cart(
    @SerializedName("sepet_yemek_id") @Expose var cartFoodId:String,
    @SerializedName("yemek_adi") @Expose var foodName: String,
    @SerializedName("yemek_resim_adi") @Expose var foodPicture: String,
    @SerializedName("yemek_fiyat") @Expose var foodPrice: String,
    @SerializedName("yemek_siparis_adet") @Expose var foodQuantity: String,
    @SerializedName("kullanici_adi") @Expose var orderUsername: String,
):Serializable
