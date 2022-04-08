package com.example.parking.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parking.Data.Customer
import com.example.parking.Data.Parking
import com.example.parking.R
import com.example.parking.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {

    lateinit var binding: ActivityConfirmationBinding
    lateinit var currentCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var code = randomID()

        binding.code.setText(code)

        var spotDetail = intent.getSerializableExtra("SELECTED_PARKING") as Parking
        currentCustomer = intent.getSerializableExtra("CURRENT_CUSTOMER") as Customer

        if(currentCustomer.isGuest) {
            binding.confirmCodeHeader.setText("Hello Guest, Take a screenshot!")
        }

        if (currentCustomer.isGuest) {
            binding.home.setOnClickListener {
                val homeIntent = Intent(this, MainActivity::class.java)
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(homeIntent)
            }
        } else {
            binding.home.setOnClickListener {
                val homeIntent = Intent(this, HomeActivity::class.java)
                spotDetail.confirmation = code
                currentCustomer.reservations.add(spotDetail)
                homeIntent.putExtra("RESERVED_SPOT", spotDetail)
                homeIntent.putExtra("CURRENT_CUSTOMER", currentCustomer)
                homeIntent.putExtra("SOURCE", "ConfirmActivity")
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(homeIntent)
            }
        }

    }


    // To generate the random confirmation
    private fun randomID(): String = List(10) {
        (('A'..'Z') + ('0'..'9')).random()
    }.joinToString("")
}