package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.adapters.CartAdapter
import com.furkanakcakaya.mealbuddy.databinding.FragmentCartBinding
import com.furkanakcakaya.mealbuddy.entities.Cart
import com.furkanakcakaya.mealbuddy.viewmodels.CartViewModel
import com.furkanakcakaya.mealbuddy.viewmodels.CartViewModelFactory
import com.google.android.material.snackbar.Snackbar

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewmodel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tmp : CartViewModel by viewModels {
            CartViewModelFactory(requireActivity().application)
        }
        viewmodel = tmp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)


        viewmodel.currentCart.observe(viewLifecycleOwner) {
            binding.tbCartTitle = viewmodel.currentCart.value.totalPrice()

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
            Snackbar.make(binding.root, this.getString(R.string.clear_cart), Snackbar.LENGTH_LONG).setAction(
                this.getString(R.string.yes)
            ) {
                viewmodel.clearCart()
            }.show()
            true
        }
    }

    private fun List<Cart>?.totalPrice(): String {
        if (this!![0].foodName == requireContext().getString(R.string.cart_empty)) return requireContext().getString(R.string.cart_empty)
        var total = 0
        for (item in this) {
            total += item.foodPrice.toInt() * item.foodQuantity.toInt()
        }
        return requireContext().getString(R.string.cart_total, total)
    }

}
