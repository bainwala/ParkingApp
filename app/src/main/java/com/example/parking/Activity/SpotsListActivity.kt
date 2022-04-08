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
import com.example.parking.databinding.ActivityListViewBinding
import com.example.parking.databinding.ActivitySpotsListBinding

class SpotsListActivity : AppCompatActivity() {

    lateinit var binding: ActivitySpotsListBinding
    lateinit var currentCustomer: Customer
    lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpotsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parkingSpots = ArrayList<Parking>();
        parkingSpots.add(Parking("16374", 3.0, "GH", 50112, "McDonalds", arrayListOf<String>("05/22/2022", "05/02/2022"),""))
        parkingSpots.add(Parking("64738", 5.0, "KO", 50112, "McDonalds", arrayListOf<String>("05/22/2022", "05/02/2022"),""))
        parkingSpots.add(Parking("79253", 3.0, "AB", 52736, "Caseys", arrayListOf<String>("05/22/2022", "05/02/2022"),""))
        parkingSpots.add(Parking("64738", 2.0, "DB", 52736, "Starbucks", arrayListOf<String>("05/22/2022", "05/02/2022"),""))

        val zip = intent.getIntExtra("ZIP", 0).toString();
        val date = intent.getStringExtra("DATE")
        currentCustomer = intent.getSerializableExtra("CURRENT_CUSTOMER") as Customer
        binding.SpotListIntroMessage.setText("Zip Entered: $zip")
        binding.SpotListIntroMessage2.setText("Date Entered: $date")

        parkingSpots.forEach {
            if(it.zipCode == zip.toInt() && it.availableDates?.contains(date) == true) {
                addDetailsElement(it)
            }
        }

    }

    private fun addDetailsElement(spotDetail: Parking) {
        val detailsLayoutBinding = ActivityListViewBinding.inflate(layoutInflater)
        detailsLayoutBinding.Owner.setText(spotDetail.ownership)
        detailsLayoutBinding.SpotsAvailable.setText("Time Available = ${spotDetail.totalTimeAvailable} Hrs")
        detailsLayoutBinding.reserveSpot.visibility = View.VISIBLE
        detailsLayoutBinding.reserveSpot.setOnClickListener {
            val confirmIntent = Intent(this, ConfirmationActivity::class.java)
            confirmIntent.putExtra("SELECTED_PARKING", spotDetail)
            confirmIntent.putExtra("CURRENT_CUSTOMER", currentCustomer)
            confirmIntent.putExtra("SELECTED_DATE", date)
            startActivity(confirmIntent)
        }
        binding.spotsList.addView(detailsLayoutBinding.root)
    }


}