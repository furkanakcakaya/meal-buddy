package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.adapters.CartAdapter
import com.furkanakcakaya.mealbuddy.databinding.FragmentCartBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.viewmodels.CartViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewmodel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tmp : CartViewModel by viewModels()
        viewmodel = tmp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewmodel.currentCart.observe(viewLifecycleOwner) {
            binding.adapter = CartAdapter(requireContext(), it, viewmodel)
        }

        binding.username = viewmodel.getUsername()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.cart_toolbar_menu, menu)
        val item = menu.findItem(R.id.clearCart)
        item.setOnMenuItemClickListener {
            viewmodel.clearCart()
            true
        }
    }
}