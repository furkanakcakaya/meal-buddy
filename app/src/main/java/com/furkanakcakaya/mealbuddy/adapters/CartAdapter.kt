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
import com.furkanakcakaya.mealbuddy.viewmodels.CartViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CartAdapter (
    private var mContext: Context,
    private val cartList: List<Cart>,
    private val viewModel: CartViewModel
        ): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : CartItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.cart_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartList[position]
        holder.binding.cartItem = cartItem

        if (cartItem.foodPicture == "") {
            holder.binding.ivCart.setImageResource(R.drawable.cart)
            holder.binding.cvCount.visibility = ViewGroup.GONE
            holder.binding.tvCartPrice.visibility = ViewGroup.GONE
            holder.binding.ivRemove.visibility = ViewGroup.GONE
        } else {
            loadImage(holder.binding.ivCart,cartItem.foodPicture)

            holder.binding.ivRemove.setOnClickListener {
                removeItem(holder.binding, cartItem)
            }

            holder.binding.ivCartDecrease.setOnClickListener {
                if (cartItem.foodQuantity.toInt() > 1){
                    cartItem.foodQuantity = (cartItem.foodQuantity.toInt() - 1).toString()
                    holder.binding.cartItem = cartItem
                    viewModel.updateCartItem(cartItem)
                }else{
                    removeItem(holder.binding, cartItem)
                }
            }

            holder.binding.ivCartIncrease.setOnClickListener {
                if (cartItem.foodQuantity.toInt() < 9){
                    cartItem.foodQuantity = (cartItem.foodQuantity.toInt() + 1).toString()
                    holder.binding.cartItem = cartItem
                    viewModel.updateCartItem(cartItem)
                }else{
                    Snackbar.make(holder.binding.root, "Maximum quantity is 9", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    private fun loadImage(view: ImageView, imageUrl: String){
        Picasso.get()
            .load("http://kasimadalan.pe.hu/yemekler/resimler/$imageUrl")
            .into(view)
    }

    private fun removeItem(binding: CartItemBinding, cartItem: Cart){
        Snackbar.make(binding.root, "${cartItem.foodName} silinsin mi?", Snackbar.LENGTH_LONG).setAction(
            "Evet"
        ) {
            viewModel.removeCartItem(cartItem.cartFoodId, cartItem.orderUsername)
        }.show()
    }
}