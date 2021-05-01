package com.kangdroid.k_labapplication.data.dto.response

data class ClassResponse(
    var title: String,
    var neededThings: String,
    var maximumUsers: Int,
    var currentRegisteredUsers: Int,
    var classToken: String
)