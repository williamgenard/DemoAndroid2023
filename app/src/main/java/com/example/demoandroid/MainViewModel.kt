package com.example.demoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoandroid.models.User

class MainViewModel : ViewModel() {
    private val _user : MutableLiveData<User> = MutableLiveData(User())
    val user : LiveData<User>
        get() = _user

    fun setFullName(firstName : String, lastName : String) {
        val newValue = _user.value!!
        newValue.firstName = firstName
        newValue.lastName = lastName
        _user.value = newValue
    }

    fun setPassword(password : String) {
        val newValue = _user.value!!
        newValue.password = password
        _user.value = newValue
    }

    fun addDiploma(diploma : String) {
        val newValue = _user.value!!
        newValue.diplomas.add(diploma)
        _user.value = newValue
    }

    fun removeDiploma(position : Int) {
        val newValue = _user.value!!
        newValue.diplomas.removeAt(position)
        _user.value = newValue
    }
}