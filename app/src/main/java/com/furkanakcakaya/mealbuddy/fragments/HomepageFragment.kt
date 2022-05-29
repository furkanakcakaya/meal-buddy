package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.adapters.FoodAdapter
import com.furkanakcakaya.mealbuddy.databinding.FragmentHomepageBinding
import com.furkanakcakaya.mealbuddy.viewmodels.HomepageViewModel
import com.furkanakcakaya.mealbuddy.viewmodels.HomepageViewModelFactory

class HomepageFragment : Fragment() {
    private lateinit var binding:FragmentHomepageBinding
    private lateinit var viewModel: HomepageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp : HomepageViewModel by viewModels {
            HomepageViewModelFactory(requireActivity().application)
        }
        viewModel = tmp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_homepage, container, false)

        viewModel.foodList.observe(viewLifecycleOwner){
            binding.adapter = FoodAdapter(requireContext(), it, viewModel)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                if (p0 != null) {
                    viewModel.searchFood(p0)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.searchFood(p0)
                }
                return true
            }


        })

        return binding.root
    }

}