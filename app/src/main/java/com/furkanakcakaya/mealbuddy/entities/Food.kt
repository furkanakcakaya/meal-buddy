package com.furkanakcakaya.mealbuddy.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Food(
    @SerializedName("yemek_id") @Expose var id: String,
    @SerializedName("yemek_adi") @Expose var name: String,
    @SerializedName("yemek_resim_adi") @Expose var picName: String,
    @SerializedName("yemek_fiyat") @Expose var price: String
) : Serializable
