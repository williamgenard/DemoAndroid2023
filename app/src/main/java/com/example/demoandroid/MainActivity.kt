package com.example.demoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoandroid.adapters.BookAdapter
import com.example.demoandroid.databinding.ActivityMainBinding
import com.example.demoandroid.db.entities.Book

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels { MainViewModelFactory(this) }

    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter : BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnAddMainActivity.setOnClickListener {
            val newBook = Book(
                binding.etTitleMainActivity.text.toString(),
                binding.etDescriptionMainActivity.text.toString()
            )

            viewModel.addBook(newBook)
        }

        viewModel.books.observe(this) {
            adapter.updateBooks(it)
        }

        binding.rvMainActivity.layoutManager = GridLayoutManager(this, 2)
        adapter = BookAdapter()
        binding.rvMainActivity.adapter = adapter

        setContentView(binding.root)
    }
}