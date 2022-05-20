package com.furkanakcakaya.mealbuddy.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("sepet_yemekler") @Expose var message: List<Cart>,
    @SerializedName("success") @Expose var success: Int,
    )
