package com.kangdroid.k_labapplication.data.dto.request

import java.io.Serializable

class GardenReservationRequest (
    var reservationStartTime: Long, // timestamp
    var reservationSpace: String,
): Serializable