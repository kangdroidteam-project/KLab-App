package com.kangdroid.k_labapplication.data.dto.response

data class ClassDetailResponse(
    var title: String,
    var author: String,
    var mainContent: String,
    var neededThings: String,
    var dueDate: String,
    var maximumUsers: Int,
    var contact: String
)
