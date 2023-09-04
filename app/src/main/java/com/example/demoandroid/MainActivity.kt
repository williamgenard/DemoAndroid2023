package com.example.demoandroid

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Hello App"

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnHelloMainActivity.setOnClickListener {
            val title = "Hello ${binding.etNameMainActivity.text}"
            binding.tvTitleMainActivity.text = title
            //val intent = Intent(this, MainActivity::class.java)
        }
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toasting -> {
                Toast.makeText(this, "BOUH !", Toast.LENGTH_LONG).show()
                true
            }
            R.id.log -> {
                Log.d("Main activity : ", "Log !")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}