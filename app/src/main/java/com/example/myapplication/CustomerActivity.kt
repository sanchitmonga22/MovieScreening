package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCustomerBinding

class CustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding
    private var isBookingOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)

        isBookingOpen = intent.extras?.get(MainActivity.IS_BOOKING_OPEN) as Boolean
        initUI()
        if (!isBookingOpen) {
            hideUI()
        }
        setContentView(binding.root)
    }

    private fun hideUI() {
        binding.BookingOpen.visibility = View.GONE
        binding.BookingClosed.visibility = View.VISIBLE
    }

    private fun showUI() {
        binding.BookingOpen.visibility = View.VISIBLE
        binding.BookingClosed.visibility = View.GONE
    }

    private fun initUI() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                MainActivity.CURRENT_SCREENING_MOVIES
            )
        binding.movieSelection.adapter = adapter

        binding.movieSelection.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.numberOfTicketsRemaining.text =
                        MainActivity.movies_tickets_remaining[MainActivity.CURRENT_SCREENING_MOVIES[position]].toString()
                    // get the number of tickets remaining
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        binding.numberOfTickets.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.numberOfTicketsRemaining.text =
                    MainActivity.movies_tickets_remaining[binding.movieSelection.selectedItem.toString()].toString()
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentlySelectedMovie = binding.movieSelection.selectedItem.toString()
                if (currentlySelectedMovie.isNotEmpty() && s?.isNotEmpty() == true) {
                    val remainingTickets = getRemainingTickets()?.minus(s.toString().toInt())

                    if (remainingTickets != null && remainingTickets >= 0) {
                        binding.numberOfTicketsRemaining.text = remainingTickets.toString()
                    } else {
                        binding.numberOfTicketsRemaining.text = "Error!!"
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.bookTicket.setOnClickListener {
            val selectedMovieName = binding.movieSelection.selectedItem.toString()
            val numberOfTicketsSelectedByCustomer = binding.numberOfTickets.text.toString().toInt()

            if (getRemainingTickets()!! == 0) {
                AlertDialog.Builder(this@CustomerActivity)
                    .setTitle("Error while confirming the booking")
                    .setMessage("The show has reached its limit ;(")
                    .setPositiveButton("Ok") { dialog, _ ->
                        binding.numberOfTickets.setText("")
                        dialog.dismiss()
                    }.show()
            } else if (numberOfTicketsSelectedByCustomer <= 0 || numberOfTicketsSelectedByCustomer > getRemainingTickets()!!) {
                AlertDialog.Builder(this@CustomerActivity)
                    .setTitle("Error while confirming the booking")
                    .setMessage("Please enter the valid number of tickets within the mentioned limit")
                    .setPositiveButton("Ok") { dialog, _ ->
                        binding.numberOfTickets.setText("")
                        dialog.dismiss()
                    }.show()
            } else {
                MainActivity.movies_tickets_remaining[selectedMovieName] =
                    getRemainingTickets()?.minus(numberOfTicketsSelectedByCustomer)!!
                Toast.makeText(
                    this@CustomerActivity,
                    "Booking is Confirmed for $selectedMovieName:$numberOfTicketsSelectedByCustomer tickets!!",
                    Toast.LENGTH_LONG
                )
                    .show()
                binding.numberOfTickets.setText("")
                hideKeyboard(this@CustomerActivity)
            }
        }
        // confirm the booking
    }

    private fun getRemainingTickets(): Int? =
        MainActivity.movies_tickets_remaining[binding.movieSelection.selectedItem.toString()]

    // from https://stackoverflow.com/questions/1109022/how-to-close-hide-the-android-soft-keyboard-programmatically
    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}