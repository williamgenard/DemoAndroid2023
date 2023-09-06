package com.example.demoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoandroid.adapters.ProductAdapter
import com.example.demoandroid.databinding.ActivityMainBinding
import com.example.demoandroid.models.Product

class MainActivity : AppCompatActivity(), ProductAdapter.ProductEventListener {
    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    private lateinit var adater : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setListener()
        observeVm()
        setRv()

        setContentView(binding.root)
    }

    private fun setListener() {
        binding.button.setOnClickListener {
            val newProduct = Product(
                name = binding.etProductNameMainActivity.text.toString(),
                quantity = binding.etQuantityMainActivity.text.toString().toInt()
            )
            viewModel.addProduct(newProduct)
        }
    }

    private fun observeVm() {
        viewModel.products.observe(this) {
            adater.updateProducts(it)
        }
    }

    private fun setRv() {
        binding.rvMainActivity.layoutManager = LinearLayoutManager(this)
        adater = ProductAdapter(this)
        binding.rvMainActivity.adapter = adater
    }

    override fun onDelete(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Voulez vous vraiment supprimer ${viewModel.products.value?.get(position)?.name}")
        builder.setPositiveButton("Oui") { _, _ ->
            viewModel.removeProduct(position)
        }
        builder.setNegativeButton("Non", null)
        builder.show()
    }

    override fun onEdit() {
        TODO("Not yet implemented")
    }
}