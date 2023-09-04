package com.example.demoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _counter :MutableLiveData<Int> = MutableLiveData(0)
    val counter : LiveData<Int>
        get() = _counter

    fun increaseCounter() {
        _counter.value = _counter.value!! + 1
    }

    fun deacreaseCounter() {
        _counter.value = _counter.value!! - 1
    }
}