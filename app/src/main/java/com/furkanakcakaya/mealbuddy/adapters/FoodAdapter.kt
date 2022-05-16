package com.furkanakcakaya.mealbuddy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.AllFoodsItemBinding
import com.furkanakcakaya.mealbuddy.entities.FoodItem
import com.furkanakcakaya.mealbuddy.viewmodels.HomepageViewModel

class FoodAdapter(
    private var mContext: Context,
    private var items: List<FoodItem>,
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
        val food = items.get(position)
        val b = holder.binding
        b.foodItem = food
    }

    override fun getItemCount(): Int {
        return items.size
    }

}