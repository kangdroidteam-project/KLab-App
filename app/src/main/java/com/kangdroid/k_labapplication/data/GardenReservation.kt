package com.kangdroid.k_labapplication.data

data class GardenReservation(
    var id: Long, // 자체 Key
    var reservationTime: Long,
    var reservationSpace: String,
)