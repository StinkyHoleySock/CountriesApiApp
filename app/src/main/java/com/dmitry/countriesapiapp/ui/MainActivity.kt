package com.dmitry.countriesapiapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dmitry.countriesapiapp.NetworkUtil
import com.dmitry.countriesapiapp.applyVisibility
import com.dmitry.coutriesapiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkConnection = NetworkUtil(applicationContext)
        networkConnection.observe(this) { isConnected ->
            binding.tvConnection.applyVisibility(!isConnected)
            binding.fragmentContainer.applyVisibility(isConnected)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}