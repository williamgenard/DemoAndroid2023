package com.example.demoandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoandroid.databinding.ActivityEditProductBinding
import com.example.demoandroid.databinding.ActivityMainBinding
import com.example.demoandroid.models.Product

class EditProductActivity : AppCompatActivity() {
    companion object {
        val PRODUCT_TAG = "product"
        val POSITION_TAG = "position"
    }

    private lateinit var binding: ActivityEditProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)

        val product = intent.getSerializableExtra(PRODUCT_TAG) as Product
        val position = intent.getIntExtra(POSITION_TAG, -1)

        binding.etNameEditProductActivity.setText(product.name)
        binding.etQuantityEditProductActivity.setText(product.quantity.toString())

        binding.btnAddEditProductActivity.setOnClickListener {
            val intent = Intent()
            intent.putExtra(PRODUCT_TAG, Product(
                name = binding.etNameEditProductActivity.text.toString(),
                quantity = binding.etQuantityEditProductActivity.text.toString().toInt()
            ))
            intent.putExtra(POSITION_TAG, position)
            setResult(RESULT_OK, intent)
            finish()
        }

        setContentView(binding.root)
    }
}