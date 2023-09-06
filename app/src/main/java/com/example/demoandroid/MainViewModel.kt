package com.example.demoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoandroid.models.Product

class MainViewModel : ViewModel() {
    private val _products : MutableLiveData<List<Product>> = MutableLiveData(mutableListOf())
    val products : LiveData<List<Product>>
        get() = _products

    fun addProduct(product : Product) {
        val newValue = _products.value?.toMutableList()
        if (newValue != null) {
            newValue.add(product)
            _products.value = newValue.toList()
        }
    }

    fun removeProduct(position : Int) {
        val newValue = _products.value!!.toMutableList()
        newValue.removeAt(position)
        _products.value = newValue
    }
}