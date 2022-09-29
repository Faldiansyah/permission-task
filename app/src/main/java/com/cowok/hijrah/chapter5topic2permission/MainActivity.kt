package com.cowok.hijrah.chapter5topic2permission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cowok.hijrah.chapter5topic2permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            Glide.with(this)
                .load("https://www.muslimobsession.com/wp-content/uploads/2020/06/IMG_20200619_083259.jpg")
                .circleCrop()
                .into(binding.imgLoad)
        }

        binding.buttonToAlarm.setOnClickListener {
            startActivity(Intent(this, AlarmActivity::class.java))
        }

        binding.buttonCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

    }
}