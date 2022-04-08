package com.example.parking.Data

import android.net.wifi.WifiManager
import java.io.Serializable

data class Customer(
    val isGuest: Boolean,
    val customerId: String,
    val firstName: String,
    val lastName: String,
    val dob: String?,
    val reservations: ArrayList<Parking>
) : Serializable


