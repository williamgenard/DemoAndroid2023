package com.example.demoandroid.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.api.LoginAPI
import com.example.demoandroid.api.RetrofitClient
import com.example.demoandroid.ui.login.LoginViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit

class MainViewModel(client : Retrofit) : ViewModel() {

    private val api = client.create(LoginAPI::class.java)

    private val _message : MutableLiveData<String> = MutableLiveData("")
    val message : LiveData<String>
        get() = _message

    fun testPermitAll() {
        viewModelScope.launch {
            try {
                val result = api.testPermitAll()
                _message.value = result
            }
            catch (e: HttpException) {
                _message.value = e.message()
            }
        }
    }

    fun testAuthenticated() {
        viewModelScope.launch {
            try {
                val result = api.testAuthenticated()
                _message.value = result
            }
            catch (e: HttpException) {
                _message.value = e.message()
            }
        }
    }

    fun testAdmin() {
        viewModelScope.launch {
            try {
                val result = api.testAdmin()
                _message.value = result
            }
            catch (e : HttpException) {
                _message.value = e.message.toString()
            }
        }
    }
}


class MainViewModelFactory(val context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(RetrofitClient.getClient(context)) as T
    }
}