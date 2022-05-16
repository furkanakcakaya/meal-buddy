package com.furkanakcakaya.mealbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.furkanakcakaya.mealbuddy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val bottomNavHost = supportFragmentManager.findFragmentById(R.id.bottomNavHost) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomBar, bottomNavHost.navController)

    }
}