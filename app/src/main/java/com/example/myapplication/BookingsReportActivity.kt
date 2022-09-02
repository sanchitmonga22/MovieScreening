package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityBookingsReportBinding

class BookingsReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingsReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingsReportBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI() {
        binding.totalTicketsSold.text = getTotatNumberOfTicketsSold().toString()
        binding.salesForScreen1.text =
            "${getString(R.string.sales_for_screen_1)}: ${getTotalTicketsForScreen(MainActivity.AVAILABLE_SCREENS[0])}"
        binding.salesForScreen2.text =
            "${getString(R.string.sales_for_screen_2)}: ${getTotalTicketsForScreen(MainActivity.AVAILABLE_SCREENS[1])}"
        binding.salesForScreen3.text =
            "${getString(R.string.sales_for_screen_3)}: ${getTotalTicketsForScreen(MainActivity.AVAILABLE_SCREENS[2])}"
        binding.salesForScreen4.text =
            "${getString(R.string.sales_for_screen_4)}: ${getTotalTicketsForScreen(MainActivity.AVAILABLE_SCREENS[3])}"
        binding.salesForScreen5.text =
            "${getString(R.string.sales_for_screen_5)}: ${getTotalTicketsForScreen(MainActivity.AVAILABLE_SCREENS[4])}"


        binding.salesForMovie1.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[0]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[0])}"
        binding.salesForMovie2.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[1]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[1])}"
        binding.salesForMovie3.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[2]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[2])}"
        binding.salesForMovie4.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[3]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[3])}"
        binding.salesForMovie5.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[4]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[4])}"
        binding.salesForMovie6.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[5]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[5])}"
        binding.salesForMovie7.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[6]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[6])}"
        binding.salesForMovie8.text =
            "${MainActivity.CURRENT_SCREENING_MOVIES[7]}: ${getTotalTicketsForMovies(MainActivity.CURRENT_SCREENING_MOVIES[7])}"


        binding.endTheDay.setOnClickListener {
            MainActivity.movie_tickets_sold_per_screen = mutableMapOf()
            finish()
        }
    }

    private fun getTotalTicketsForScreen(screenName: String): Int {
        var tickets = 0
        MainActivity.movie_tickets_sold_per_screen[screenName]?.forEach { it ->
            tickets += it.value
        }
        return tickets
    }

    private fun getTotalTicketsForMovies(movieName: String): Int {
        var totalTickets = 0
        MainActivity.movie_tickets_sold_per_screen.forEach {
            it.value.forEach { it1 ->
                if (it1.key == movieName) {
                    totalTickets += it1.value
                }
            }
        }
        return totalTickets
    }

    private fun getTotatNumberOfTicketsSold(): Int {
        var totalTickets = 0
        MainActivity.movie_tickets_sold_per_screen.forEach { it ->
            it.value.forEach { it1 ->
                totalTickets += it1.value
            }
        }
        return totalTickets
    }
}