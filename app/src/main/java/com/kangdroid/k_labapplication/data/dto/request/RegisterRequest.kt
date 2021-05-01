package com.kangdroid.k_labapplication.data.dto.request

data class RegisterRequest(
    var userId: String,
    var userName: String,
    var userAddress: String,
    var userPhoneNumber: String,
    var userPassword: String,
    var userAgreementAgreed: Boolean
)
