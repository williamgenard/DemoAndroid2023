package com.example.demoandroid

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val pushNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            runNotificationService()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnStartServiceMainActivity.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pushNotificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
            else {
                runNotificationService()
            }
        }

        binding.btnStopServiceMainActivity.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        setContentView(binding.root)
    }

    private fun runNotificationService() {
        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }
}