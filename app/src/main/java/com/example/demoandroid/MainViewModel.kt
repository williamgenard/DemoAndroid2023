package com.example.demoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _counter :MutableLiveData<Int> = MutableLiveData(0)
    val counter : LiveData<Int>
        get() = _counter

    private val _eventList : MutableLiveData<List<String>> = MutableLiveData(listOf())
    val eventList : LiveData<List<String>>
        get() = _eventList

    fun increaseCounter() {
        _counter.value = _counter.value!! + 1

        val newValues = _eventList.value!!.toMutableList()
        newValues.add("Increase !")
        _eventList.value = newValues
    }

    fun deacreaseCounter() {
        _counter.value = _counter.value!! - 1

        val newValues = _eventList.value!!.toMutableList()
        newValues.add("Decrease !")
        _eventList.value = newValues
    }
}