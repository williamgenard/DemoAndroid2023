package com.example.demoandroid.api.models

import com.google.gson.annotations.SerializedName

data class Prediction(
    val age: Int,
    val count: Int,
    @SerializedName("name")
    val name: String
)