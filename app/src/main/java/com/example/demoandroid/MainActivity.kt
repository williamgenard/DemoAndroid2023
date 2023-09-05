package com.example.demoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatViewInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoandroid.adapters.EventAdapter
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    private lateinit var adapter : EventAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        observeVm()
        setListeners()
        setRv()

        setContentView(binding.root)
    }
    private fun observeVm() {
        viewModel.counter.observe(this) {
            binding.tvCounter.text = it.toString()
        }

        viewModel.eventList.observe(this) {
            adapter.updateEvents(it)
        }
    }

    private fun setListeners() {
        binding.btnPlus.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Voulez-vous vraiment incrémenter le compteur ?")
            builder.setPositiveButton("Oui") { _, _ ->
                viewModel.increaseCounter()
            }
            builder.setNegativeButton("Non", null)
            builder.show()
        }

        binding.btnMinus.setOnClickListener {
            viewModel.deacreaseCounter()
        }
    }

    private fun setRv() {
        binding.rvMainActivity.layoutManager = LinearLayoutManager(this)
        adapter = EventAdapter {
            Log.d("Main activyt", it)
        }
        binding.rvMainActivity.adapter = adapter
    }


}