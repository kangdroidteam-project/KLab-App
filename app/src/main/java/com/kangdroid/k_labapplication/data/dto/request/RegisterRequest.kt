package com.kangdroid.k_labapplication.data.dto.request

data class RegisterRequest(
    var userId: String,
    var userPassword: String,
    var userName: String,
    var userAddress: String,
    var userPhoneNumber: String,
    var userIntroduction: String,
)