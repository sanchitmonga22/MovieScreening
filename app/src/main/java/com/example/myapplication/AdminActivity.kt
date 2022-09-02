package com.example.myapplication

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAdminPageBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPageBinding.inflate(layoutInflater)
        initUI()
        initData()
        setContentView(binding.root)
    }

    private fun initUI() {
        initSpinners()
        binding.startBooking.setOnClickListener {
            initData()
            initNumberOfTicketsAvailable()
            if (bookingCheck()) {
                MainActivity.isBookingOpen = true
                // resetting the screens
                MainActivity.movies_tickets_remaining.forEach {
                    MainActivity.movies_tickets_remaining[it.key] =
                        MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
                }
                initData()
                this@AdminActivity.finish()
                Toast.makeText(this@AdminActivity, "Booking has been started", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.endBooking.setOnClickListener {
            MainActivity.isBookingOpen = false
            startActivity(Intent(this@AdminActivity, BookingsReportActivity::class.java))
        }
    }

    private fun initData() {
        // clearing the map everytime
        MainActivity.movies_tickets_remaining.clear()
        MainActivity.movie_to_screen_map.clear()

        initMovieToScreens()

        initNumberOfTicketsAvailable()
    }

    private fun initNumberOfTicketsAvailable() {
        MainActivity.movies_tickets_remaining[binding.screen1Selection.selectedItem.toString()] =
            MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
        MainActivity.movies_tickets_remaining[binding.screen2Selection.selectedItem.toString()] =
            MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
        MainActivity.movies_tickets_remaining[binding.screen3Selection.selectedItem.toString()] =
            MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
        MainActivity.movies_tickets_remaining[binding.screen4Selection.selectedItem.toString()] =
            MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
        MainActivity.movies_tickets_remaining[binding.screen5Selection.selectedItem.toString()] =
            MainActivity.INITIAL_TOTAL_NUMBER_OF_TICKETS_PER_SCREEN
    }

    private fun initMovieToScreens() {
        MainActivity.movie_to_screen_map[binding.screen1Selection.selectedItem.toString()] =
            MainActivity.AVAILABLE_SCREENS[0]
        MainActivity.movie_to_screen_map[binding.screen2Selection.selectedItem.toString()] =
            MainActivity.AVAILABLE_SCREENS[1]
        MainActivity.movie_to_screen_map[binding.screen3Selection.selectedItem.toString()] =
            MainActivity.AVAILABLE_SCREENS[2]
        MainActivity.movie_to_screen_map[binding.screen4Selection.selectedItem.toString()] =
            MainActivity.AVAILABLE_SCREENS[3]
        MainActivity.movie_to_screen_map[binding.screen5Selection.selectedItem.toString()] =
            MainActivity.AVAILABLE_SCREENS[4]
    }


    private fun initSpinners() {
        val adapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        val adapter3: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        val adapter4: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        val adapter5: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        binding.screen1Selection.adapter = adapter1
        binding.screen2Selection.adapter = adapter2
        binding.screen3Selection.adapter = adapter3
        binding.screen4Selection.adapter = adapter4
        binding.screen5Selection.adapter = adapter5
    }

    override fun onBackPressed() {
        Toast.makeText(
            this@AdminActivity,
            "Please start the new booking before exiting the screen!",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun bookingCheck(): Boolean {
        val currentCount = MainActivity.movie_to_screen_map.keys.count()
        return if (currentCount != MainActivity.AVAILABLE_SCREENS.count()) {
            Toast.makeText(
                this@AdminActivity,
                "Please select a different movie for each screen",
                Toast.LENGTH_LONG
            ).show()
            false
        } else {
            true
        }
    }
}