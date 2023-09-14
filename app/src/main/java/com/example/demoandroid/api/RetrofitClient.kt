package com.example.demoandroid.api

import android.content.Context
import com.example.demoandroid.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RequestInterceptor(context: Context) : Interceptor {
    private val tokenManager : TokenManager = TokenManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = tokenManager.getAuthToken()

        if (token != null) {
            requestBuilder.addHeader(
                "Authorization", "Bearer $token"
            )
        }

        return chain.proceed(requestBuilder.build())
    }

}

object RetrofitClient {
    private val BASE_URL = "http://10.0.2.2:8080/"

    private lateinit var client : Retrofit

    fun getClient(context : Context) : Retrofit {
        if (!this::client.isInitialized) {
            client = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient(context))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return client
    }

    private fun okHttpClient(context : Context) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor(context))
            .build()
    }
}