package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.FragmentAccountBinding
import com.furkanakcakaya.mealbuddy.viewmodels.AccountViewModel
import com.furkanakcakaya.mealbuddy.viewmodels.AccountViewModelFactory

class AccountFragment : Fragment() {
    private lateinit var binding:FragmentAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp: AccountViewModel by viewModels() {
            AccountViewModelFactory(requireActivity().application)
        }
        viewModel = tmp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false)
        binding.fragment = this
        viewModel.username.observe(viewLifecycleOwner) {
            binding.username = it
        }
        return binding.root
    }

    fun saveButton(username:String){
        viewModel.updateUsername(username)
    }
}