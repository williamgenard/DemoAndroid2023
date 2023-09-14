package com.example.demoandroid.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.api.LoginAPI
import com.example.demoandroid.api.RetrofitClient
import com.example.demoandroid.api.models.LoginForm
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit

class LoginViewModel(client : Retrofit) : ViewModel() {

    private val api = client.create(LoginAPI::class.java)

    private val _token : MutableLiveData<String?> = MutableLiveData(null)
    val token : LiveData<String?>
        get() = _token

    private val _error : MutableLiveData<Int?> = MutableLiveData(null)
    val error : LiveData<Int?>
        get() = _error

    fun login(loginForm : LoginForm) {
        viewModelScope.launch {
            try {
                val result = api.login(loginForm)
                _token.value = result.token
            }
            catch (e: HttpException) {
                _error.value = e.code()
            }
        }
    }

    fun register(loginForm: LoginForm) {
        viewModelScope.launch {
            try {
                val result = api.register(loginForm)
                _token.value = result.token
            }
            catch (e: HttpException) {
                _error.value = e.code()
            }
        }
    }
}

class LoginViewModelFactory(val context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(RetrofitClient.getClient(context)) as T
    }
}