package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.adapters.FoodAdapter
import com.furkanakcakaya.mealbuddy.databinding.FragmentHomepageBinding
import com.furkanakcakaya.mealbuddy.viewmodels.HomepageViewModel

class HomepageFragment : Fragment() {
    private lateinit var binding:FragmentHomepageBinding
    private lateinit var viewModel: HomepageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp : HomepageViewModel by viewModels()
        viewModel = tmp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_homepage, container, false)
        binding.toolbarTitle = "Homepage"

        viewModel.foodList.observe(viewLifecycleOwner){
            binding.adapter = FoodAdapter(requireContext(), it, viewModel)
        }

        return binding.root
    }

}