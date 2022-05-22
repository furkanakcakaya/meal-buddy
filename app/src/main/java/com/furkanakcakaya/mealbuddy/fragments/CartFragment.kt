package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.adapters.CartAdapter
import com.furkanakcakaya.mealbuddy.databinding.FragmentCartBinding
import com.furkanakcakaya.mealbuddy.viewmodels.CartViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewmodel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp : CartViewModel by viewModels()
        viewmodel = tmp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)

        viewmodel.currentCart.observe(viewLifecycleOwner) {
            binding.adapter = CartAdapter(requireContext(), it, viewmodel)
        }


        return binding.root
    }
}