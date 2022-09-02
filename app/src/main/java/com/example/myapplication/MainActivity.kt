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

        val AVAILABLE_SCREENS = arrayOf("Screen 1", "Screen 2", "Screen 3", "Screen 4", "Screen 5")

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

        val movie_to_screen_map = mutableMapOf<String, String>()
        val movies_tickets_remaining = mutableMapOf<String, Int>()

        // <Screen, <Movie, Tickets>>
        var movie_tickets_sold_per_screen = mutableMapOf<String, MutableMap<String, Int>>()

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