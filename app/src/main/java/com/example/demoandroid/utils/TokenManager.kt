package com.example.demoandroid.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.demoandroid.R

class TokenManager(context : Context) {
    private var prefs : SharedPreferences = context
        .getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )

    companion object {
        const val USER_TOKEN = "token"
        const val USERNAME = "username"
    }

    fun saveAuthToken(token : String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveUsername(username : String) {
        val editor = prefs.edit()
        editor.putString(USERNAME, username)
        editor.apply()
    }

    fun getAuthToken() : String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun getUsername() : String? {
        return prefs.getString(USERNAME, null)
    }
}