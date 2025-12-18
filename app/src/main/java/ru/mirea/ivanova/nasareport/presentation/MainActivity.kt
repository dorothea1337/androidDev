package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import ru.mirea.ivanova.nasareport.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.navHostFragment.id) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
    }
}