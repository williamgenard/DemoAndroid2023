package com.example.demoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setListeners()

        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reset -> {
                counter = 0
                binding.tvCounterMainActivity.text = counter.toString()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setListeners() {
        binding.btnPlus.setOnClickListener {
            counter++
            binding.tvCounterMainActivity.text = counter.toString()
        }

        binding.btnMinus.setOnClickListener {
            counter--
            binding.tvCounterMainActivity.text = counter.toString()
        }
    }
}