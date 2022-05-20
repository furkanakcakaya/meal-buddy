package com.furkanakcakaya.mealbuddy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.AllFoodsItemBinding
import com.furkanakcakaya.mealbuddy.entities.Food
import com.furkanakcakaya.mealbuddy.fragments.HomepageFragmentDirections
import com.furkanakcakaya.mealbuddy.viewmodels.HomepageViewModel

class FoodAdapter(
    private var mContext: Context,
    private var items: List<Food>,
    var viewModel: HomepageViewModel
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private val TAG = "FoodAdapter"
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

        b.cvFood.setOnClickListener {
            val nav = HomepageFragmentDirections.actionHomepageFragmentToFoodDetailFragment(food)
            Navigation.findNavController(it).navigate(nav)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}