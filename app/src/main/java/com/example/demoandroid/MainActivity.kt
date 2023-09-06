package com.example.demoandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
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

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val product = it.data?.getSerializableExtra(EditProductActivity.PRODUCT_TAG) as Product
            val position = it.data?.getIntExtra(EditProductActivity.POSITION_TAG, -1)
            if (position != -1 && position != null) {
                viewModel.updateProduct(
                    product,
                    position
                )
            }
        }
    }

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

    override fun onEdit(product : Product, position : Int) {
        val intent = Intent(this, EditProductActivity::class.java)
        intent.putExtra(EditProductActivity.PRODUCT_TAG, product)
        intent.putExtra(EditProductActivity.POSITION_TAG, position)
        startForResult.launch(intent)
    }
}