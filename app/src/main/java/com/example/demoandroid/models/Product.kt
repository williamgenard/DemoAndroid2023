package com.example.demoandroid.models

import java.io.Serializable

data class Product (
    val name : String,
    val quantity : Int
) : Serializable