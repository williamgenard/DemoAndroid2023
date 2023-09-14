package com.example.demoandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.demoandroid.R
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels { MainViewModelFactory(this) }
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnTestPermitAll.setOnClickListener {
            viewModel.testPermitAll()
        }

        binding.btnTestAuthenticated.setOnClickListener {
            viewModel.testAuthenticated()
        }

        binding.btnTestAdmin.setOnClickListener {
            viewModel.testAdmin()
        }

        viewModel.message.observe(this) {
            binding.tvMessageMainActivity.text = it
        }

        setContentView(binding.root)
    }
}