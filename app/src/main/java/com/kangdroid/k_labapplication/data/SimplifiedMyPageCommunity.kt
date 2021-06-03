package com.kangdroid.k_labapplication.data

data class SimplifiedMyPageCommunity(
    var id: Long, // Class ID
    var contentTitle: String, // 제목
    var startTime: Long,
    var contentNeeds: String, // 무엇이 필요한가
    var isRequestConfirmed: Boolean
)