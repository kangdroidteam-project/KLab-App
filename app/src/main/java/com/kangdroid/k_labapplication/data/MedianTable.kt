package com.kangdroid.k_labapplication.data

data class MedianTable(
    var userId: Long, // [User PK]
    var communityId: Long, // [Community PK],
    var isRequestConfirmed: Boolean
)