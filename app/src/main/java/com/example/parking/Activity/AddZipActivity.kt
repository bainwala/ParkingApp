package com.example.parking.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.parking.Data.Customer
import com.example.parking.Data.Parking
import com.example.parking.R
import com.example.parking.databinding.ActivityAddZipBinding
import java.lang.Exception

class AddZipActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddZipBinding

    lateinit var currentCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddZipBinding.inflate(layoutInflater);
        setContentView(binding.root);

        currentCustomer = intent.getSerializableExtra("CURRENT_CUSTOMER") as Customer

        binding.ZipContinue.setOnClickListener {
            try {
                if(binding.zip.text.length == 5) {
                    if(binding.date.text.isNotEmpty()) {
                        val spotListIntent = Intent(this, SpotsListActivity::class.java)
                        spotListIntent.putExtra("ZIP", binding.zip.text.toString().toInt())
                        spotListIntent.putExtra("DATE", binding.date.text.toString())
                        spotListIntent.putExtra("CURRENT_CUSTOMER", currentCustomer)
                        startActivity(spotListIntent)
                    } else {
                        binding.date.error = "Cannot be empty"
                    }
                } else {
                    binding.zip.error = "Zip Code is 5 digits!"
                }
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }

    }

}