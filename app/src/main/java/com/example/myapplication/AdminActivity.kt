package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAdminPageBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPageBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI() {
        binding.startBooking.setOnClickListener {
            MainActivity.isBookingOpen = true

            // resetting the screens
            MainActivity.movies_tickets_remaining.forEach {
                MainActivity.movies_tickets_remaining[it.key] =
                    MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
            }

            this@AdminActivity.finish()
            Toast.makeText(this@AdminActivity, "Booking has been started", Toast.LENGTH_LONG).show()
        }

        binding.endBooking.setOnClickListener {
            MainActivity.isBookingOpen = false
            startActivity(Intent(this@AdminActivity, BookingsReportActivity::class.java))
        }
    }
}