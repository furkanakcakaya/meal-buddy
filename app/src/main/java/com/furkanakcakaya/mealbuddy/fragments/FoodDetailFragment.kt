package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.FragmentFoodDetailBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.viewmodels.FoodDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FoodDetailFragment : Fragment() {
    private val TAG = "FoodDetailFragment"
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp : FoodDetailViewModel by viewModels()
        viewModel = tmp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail, container, false)
        binding.foodDetailFragment = this
        binding.cartObject = FoodDetailFragmentArgs.fromBundle(requireArguments()).currentCart

        loadImage(binding.ivFoodDetail, binding.cartObject!!.foodPicture)
        return binding.root
    }

    private fun loadImage(view:ImageView, imageUrl: String){
        Picasso.get()
            .load("http://kasimadalan.pe.hu/yemekler/resimler/$imageUrl")
            .placeholder(R.drawable.placeholder)
            .resize(512,512)
            .into(view)
    }

    fun increaseCount(cartObject: Cart){
        if (cartObject.foodQuantity.toInt() < 9){
            cartObject.foodQuantity = (cartObject.foodQuantity.toInt() + 1).toString()
            binding.cartObject = cartObject
        }else{
            Snackbar.make(binding.root, "Maximum quantity is 9", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun decreaseCount(cartObject: Cart){
        if (cartObject.foodQuantity.toInt() > 0){
            cartObject.foodQuantity = (cartObject.foodQuantity.toInt() - 1).toString()
            binding.cartObject = cartObject
        }else{
            Snackbar.make(binding.root,"Minimum quantity is 0",Snackbar.LENGTH_SHORT).show()
        }
    }

    fun buttonAddToCart(cartObject: Cart){
        if (cartObject.foodQuantity.toInt() > 0){
            viewModel.addToCart(cartObject)
        }else{
            Snackbar.make(binding.root,"You can't add zero quantity to your cart.",Snackbar.LENGTH_SHORT).show()
        }
    }
}