package com.example.demoandroid.api

import com.example.demoandroid.api.models.Prediction
import retrofit2.http.GET
import retrofit2.http.Query

interface AgifyAPI {
    @GET("/")
    suspend fun predict(@Query("name") name : String) : Prediction
}