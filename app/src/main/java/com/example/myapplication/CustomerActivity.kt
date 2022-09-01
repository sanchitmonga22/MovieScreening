package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCustomerBinding


class CustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        binding.movieSelection.adapter = adapter

        binding.bookTicket.setOnClickListener {
            val selectedMovieName = binding.movieSelection.selectedItem.toString()
            val numberOfTicketsSelectedByCustomer = binding.numberOfTickets.text.toString().toInt()
            if (numberOfTicketsSelectedByCustomer <= 0) //  || numberOfTicketsSelectedByCustomer < ) , check for the limit of available tickets
                AlertDialog.Builder(this@CustomerActivity)
                    .setTitle("Error while confirming the booking")
                    .setMessage("Please enter the valid number of tickets within the mentioned limit")
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
        }
        // confirm the booking
    }
}
}