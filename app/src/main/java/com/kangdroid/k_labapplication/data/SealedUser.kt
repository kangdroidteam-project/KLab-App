package com.kangdroid.k_labapplication.data

data class SealedUser(
    var userName: String,
    var userAddress: String,
    var userPhoneNumber: String,
    var userIntroduction: String? = null,
    var isRequestConfirmed: Boolean
)