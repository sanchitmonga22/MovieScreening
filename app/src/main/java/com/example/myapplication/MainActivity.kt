package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {

         const val INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN = 30
        const val IS_BOOKING_OPEN = "BookingOpen"
        val CURRENT_SCREENING_MOVIES = arrayOf(
            "Pulp Fiction",
            "The Shawshank Redemption",
            "The Godfather",
            "Forrest Gump",
            "Inception",
            "The Matrix",
            "Interstellar",
            "Back to the Future"
        )

        val movies_tickets_remaining = mutableMapOf(
            (CURRENT_SCREENING_MOVIES[0] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[1] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[2] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[3] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[4] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[5] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[6] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            (CURRENT_SCREENING_MOVIES[7] to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN)
        )
        var isBookingOpen = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI() {
        binding.adminButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AdminActivity::class.java))
        }

        binding.customerButton.setOnClickListener {
            val customerIntent = Intent(this@MainActivity, CustomerActivity::class.java)
            customerIntent.putExtra(IS_BOOKING_OPEN, isBookingOpen)
            startActivity(customerIntent)
        }
    }
}