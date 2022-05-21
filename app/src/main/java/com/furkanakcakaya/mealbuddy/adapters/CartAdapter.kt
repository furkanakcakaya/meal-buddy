package com.furkanakcakaya.mealbuddy.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.CartItemBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.squareup.picasso.Picasso

class CartAdapter (
    private var mContext: Context,
    private val cartList: List<Cart>,
        ): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:CartItemBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : CartItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.cart_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartList[position]
        holder.binding.cartItem = cartItem
        loadImage(holder.binding.ivCart,cartItem.foodPicture)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    private fun loadImage(view: ImageView, imageUrl: String){
        Picasso.get()
            .load("http://kasimadalan.pe.hu/yemekler/resimler/$imageUrl")
            .into(view)
    }

}