package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {

        const val INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN = 30

        val CURRENT_PLAYING_MOVIES = mapOf(
            ("The Shawshank Redemption" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("The Godfather" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("Pulp Fiction" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("Forrest Gump" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("Inception" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("The Matrix" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("Interstellar" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN),
            ("Back to the Future" to INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN)
        )

        val CURRENT_SCREENING_MOVIES = arrayOf(
            "Pulp Fiction",
            "The Shawshank Redemption",
            "The Godfather",
            "Forrest Gump",
            "Inception"
        )

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
            startActivity(Intent(this@MainActivity, CustomerActivity::class.java))
        }
    }
}