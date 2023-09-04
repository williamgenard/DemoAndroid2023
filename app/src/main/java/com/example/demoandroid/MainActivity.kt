package com.example.demoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatViewInflater
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.counter.observe(this) {
            binding.tvCounter.text = it.toString()
        }

        binding.btnPlus.setOnClickListener {
            viewModel.increaseCounter()
        }

        binding.btnMinus.setOnClickListener {
            viewModel.deacreaseCounter()
        }

        setContentView(binding.root)
    }
}