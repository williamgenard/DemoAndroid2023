package com.example.demoandroid

import android.app.Application
import android.util.Log

class BaseApplication : Application() {
    val username : String
        get() = "William"

    override fun onCreate() {
        super.onCreate()
        Log.d("Application : ", "Hello Application")
    }
}