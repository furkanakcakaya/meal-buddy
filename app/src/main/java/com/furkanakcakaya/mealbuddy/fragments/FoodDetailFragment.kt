package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.FragmentFoodDetailBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var cart: Cart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail, container, false)
        binding.foodDetailFragment = this
        cart = arguments?.getSerializable("currentCart") as Cart
        binding.cartObject = cart

            Picasso.get()
                .load("http://kasimadalan.pe.hu/yemekler/resimler/${cart.foodPicture}")
                .resize(512,512)
                .into(binding.ivFoodDetail)

        return binding.root
    }

    fun increaseCount(){
        if (cart.foodQuantity.toInt() < 9){
            cart.foodQuantity = (cart.foodQuantity.toInt() +1).toString()
            binding.cartObject = cart
        }else{
            Snackbar.make(binding.root,"Maximum quantity is 9",Snackbar.LENGTH_SHORT).show()
        }
    }

    fun decreaseCount(){
        if (cart.foodQuantity.toInt() > 0){
            cart.foodQuantity = (cart.foodQuantity.toInt() -1).toString()
            binding.cartObject = cart
        }else{
            Snackbar.make(binding.root,"Minimum quantity is 0",Snackbar.LENGTH_SHORT).show()
        }
    }
}