package com.example.demoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.demoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val AVATAR_URL = "https://xsgames.co/randomusers/avatar.php?g=pixel"
    }

    private val viewModel : MainViewModel by viewModels()

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnPredictMainActivity.setOnClickListener {
            val name = binding.etNameMainActivity.text.toString()
            viewModel.getPrediction(name)
        }

        viewModel.prediction.observe(this) {
            val message = "Age prédit : ${it.age}"

            Glide
                .with(this)
                .load(AVATAR_URL)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.imageView)

            binding.tvPredictionMainActivity.text = message
        }

        setContentView(binding.root)
    }
}