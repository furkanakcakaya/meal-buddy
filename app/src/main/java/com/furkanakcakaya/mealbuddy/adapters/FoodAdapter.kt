package com.furkanakcakaya.mealbuddy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.AllFoodsItemBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.fragments.HomepageFragmentDirections
import com.furkanakcakaya.mealbuddy.viewmodels.HomepageViewModel
import com.squareup.picasso.Picasso

class FoodAdapter(
    private var mContext: Context,
    private var items: List<Food>,
    var viewModel: HomepageViewModel
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    inner class FoodViewHolder(var binding: AllFoodsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding : AllFoodsItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.all_foods_item, parent,false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = items[position]
        val b = holder.binding
        b.foodItem = food

        changeImage(food.picName, b.ivFood)

        b.cvFood.setOnClickListener {
            val cart = if (viewModel.checkIfFoodIsInCart(food)) {
                viewModel.getCartItem(food.name)
            } else {
                Cart("-1", food.name, food.picName, food.price, "1",viewModel.getUsername())
            }
            val nav = HomepageFragmentDirections.actionHomepageFragmentToFoodDetailFragment(cart)
            Navigation.findNavController(it).navigate(nav)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun changeImage(imageName:String, imageView: android.widget.ImageView){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get()
            .load(url)
            .into(imageView)
    }

}