package com.example.demoandroid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.api.AgifyAPI
import com.example.demoandroid.api.RetrofitClient
import com.example.demoandroid.api.models.Prediction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val client = RetrofitClient.client.create(AgifyAPI::class.java)

    private val _prediction : MutableLiveData<Prediction> = MutableLiveData()
    val prediction : LiveData<Prediction>
        get() = _prediction

    fun getPrediction(name : String) {
        viewModelScope.launch {
            _prediction.value = client.predict(name)
            Log.d("View model", "coroutine scope")
        }
        Log.d("View model", "not coroutine scope")
    }
}