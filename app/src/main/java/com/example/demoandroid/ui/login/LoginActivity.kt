package com.example.demoandroid.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoandroid.R
import com.example.demoandroid.utils.TokenManager

class LoginActivity : AppCompatActivity() {

    private lateinit var tokenManage : TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        tokenManage = TokenManager(this)
//        val token = tokenManage.getAuthToken()
//
//        if (token != null) {
//
//        }
    }
}