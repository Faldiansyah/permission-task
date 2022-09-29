package com.cowok.hijrah.chapter5topic2permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cowok.hijrah.chapter5topic2permission.databinding.ActivityDestinationBinding

class DestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}