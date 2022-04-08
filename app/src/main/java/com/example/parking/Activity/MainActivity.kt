package com.example.parking.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.parking.Data.Customer
import com.example.parking.Data.Parking
import com.example.parking.R
import com.example.parking.databinding.ActivityHomeBinding
import com.example.parking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstName = binding.fName
        val lastName = binding.lName
        val dob = binding.dob

        binding.login.setOnClickListener {
            try {
                if (firstName.text.isNotEmpty()) {
                    if (lastName.text.isNotEmpty()) {
                        val homeIntent = Intent(this, HomeActivity::class.java);
                        val current_customer = Customer(
                            false,
                            randomID(),
                            firstName.text.toString(),
                            lastName.text.toString(),
                            dob.text.toString(),
                            ArrayList<Parking>()
                        )
                        homeIntent.putExtra("CURRENT_CUSTOMER", current_customer)
                        homeIntent.putExtra("SOURCE", "LoginActivity")

                        startActivity(homeIntent)
                    } else {
                        binding.lName.error = "Enter Details"
                    }
                } else {
                    binding.fName.error = "Enter Details"
                }
            } catch (e: Exception) {
                e.printStackTrace();
            }
        }

        binding.guestLogin.setOnClickListener {
            val zipIntent = Intent(this, AddZipActivity::class.java)
            val current_customer = Customer(
                true,
                "",
                "",
                "",
                "",
                ArrayList<Parking>()
            )
            zipIntent.putExtra("CURRENT_CUSTOMER", current_customer)
            startActivity(zipIntent)
        }
    }

    private fun randomID(): String = List(10) {
        (('A'..'Z') + ('0'..'9')).random()
    }.joinToString("")
}

