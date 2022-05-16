package com.furkanakcakaya.mealbuddy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.furkanakcakaya.mealbuddy.R
import com.furkanakcakaya.mealbuddy.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private lateinit var binding:FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false)
        return binding.root
    }
}