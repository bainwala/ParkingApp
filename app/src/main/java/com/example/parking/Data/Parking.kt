package com.example.parking.Data

import java.io.Serializable

data class Parking(
    var parkingId: String,
    val totalTimeAvailable: Double,
    val spotNumber: String,
    val zipCode: Int,
    val ownership: String?,
    val availableDates: ArrayList<String>?,
    var confirmation: String?,
) : Serializable

