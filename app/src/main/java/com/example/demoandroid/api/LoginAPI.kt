package com.example.demoandroid.api

import com.example.demoandroid.api.models.AuthResponse
import com.example.demoandroid.api.models.LoginForm
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginAPI {
    @POST("/signIn")
    suspend fun login(@Body loginForm: LoginForm) : AuthResponse

    @POST("/register")
    suspend fun register(@Body loginForm: LoginForm) : AuthResponse

    @GET("/testAuthenticated")
    suspend fun testAuthenticated() : String

    @GET("/testAdmin")
    suspend fun testAdmin() : String

    @GET("/testPermitAll")
    suspend fun testPermitAll() : String
}