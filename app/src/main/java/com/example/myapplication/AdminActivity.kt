package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAdminPageBinding
import com.example.myapplication.databinding.ActivityMainBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityAdminPageBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI() {
        binding.startBooking.setOnClickListener {
            MainActivity.isBookingOpen = true
        }

        binding.endBooking.setOnClickListener {
            MainActivity.isBookingOpen = false
            // launch report activity
        }
    }
}