package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.FragmentFoodDetailBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.viewmodels.FoodDetailViewModel
import com.furkanakcakaya.mealbuddy.viewmodels.FoodDetailViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp : FoodDetailViewModel by viewModels {
            FoodDetailViewModelFactory(requireActivity().application)
        }
            viewModel = tmp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
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
            Snackbar.make(binding.root, requireContext().getString(R.string.max_quantity), Snackbar.LENGTH_SHORT).show()
        }
    }

    fun decreaseCount(cartObject: Cart){
        if (cartObject.foodQuantity.toInt() > 0){
            cartObject.foodQuantity = (cartObject.foodQuantity.toInt() - 1).toString()
            binding.cartObject = cartObject
        }else{
            Snackbar.make(binding.root,requireContext().getString(R.string.min_quantity),Snackbar.LENGTH_SHORT).show()
        }
    }

    fun buttonAddToCart(cartObject: Cart){
        if (cartObject.foodQuantity.toInt() > 0){
            viewModel.addToCart(cartObject)
            val nav = FoodDetailFragmentDirections.actionFoodDetailFragmentToCartFragment()
            Navigation.findNavController(binding.root).navigate(nav)
        }else{

            Snackbar.make(binding.root,requireContext().getString(R.string.add_zero),Snackbar.LENGTH_SHORT).show()
        }
    }
}