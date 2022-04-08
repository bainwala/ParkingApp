package com.example.parking.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.parking.Data.Customer
import com.example.parking.Data.Parking
import com.example.parking.R
import com.example.parking.databinding.ActivityHomeBinding
import com.example.parking.databinding.ActivityHomeSpotsListBinding
import com.example.parking.databinding.ActivityListViewBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding;
    lateinit var currentCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root);

        if(intent.getStringExtra("SOURCE") == "ConfirmActivity") {
            currentCustomer = intent.getSerializableExtra("CURRENT_CUSTOMER") as Customer

            binding.ListMessage.setText("Here Are Your Reservations: ")
            binding.homeFirstName.setText("Hello ${currentCustomer.firstName} !")


            currentCustomer.reservations.forEach {
                addDetailsElement(it);
            }

            binding.AddReservation.setOnClickListener {
                val zipIntent = Intent(this, AddZipActivity::class.java)
                zipIntent.putExtra("CURRENT_CUSTOMER", currentCustomer)
                startActivity(zipIntent)
            }

        } else {

            currentCustomer = intent.getSerializableExtra("CURRENT_CUSTOMER") as Customer

            binding.homeFirstName.setText("Hello ${currentCustomer.firstName} !")

            if(currentCustomer.reservations.size == 0) {
                binding.ListMessage.setText("No Reservations Yet!")
            } else {
                binding.ListMessage.setText("Here Are Your Reservations")
            }

            binding.AddReservation.setOnClickListener {
                val zipIntent = Intent(this, AddZipActivity::class.java)
                zipIntent.putExtra("CURRENT_CUSTOMER", currentCustomer)
                startActivity(zipIntent)
            }

        }
    }

    private fun addDetailsElement(spotDetail: Parking) {
        val detailsLayoutBinding = ActivityHomeSpotsListBinding.inflate(layoutInflater)
        detailsLayoutBinding.location.setText("Location: ${spotDetail.ownership}")
        detailsLayoutBinding.time.setText("Duration: ${spotDetail.totalTimeAvailable.toString()}")
        detailsLayoutBinding.code.setText("Confirmation Code: ${spotDetail.confirmation}")
        binding.homeLayout.addView(detailsLayoutBinding.root)
    }
}