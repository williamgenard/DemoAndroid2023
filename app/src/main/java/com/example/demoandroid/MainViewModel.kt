package com.example.demoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _actionCounter : MutableLiveData<Int> = MutableLiveData(0)
    val actionCounter : LiveData<Int>
        get() = _actionCounter

    fun increaseCounter() {
        _actionCounter.value = _actionCounter.value!! + 1
    }
}