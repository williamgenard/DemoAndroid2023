package com.example.demoandroid.models

data class User(
    var firstName : String = "",
    var lastName : String = "",
    var password : String = "",
    var diplomas : MutableList<String> = mutableListOf()
)
