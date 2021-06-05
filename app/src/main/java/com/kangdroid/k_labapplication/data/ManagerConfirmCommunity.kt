package com.kangdroid.k_labapplication.data

data class ManagerConfirmCommunity(
    var communityTitle: String,
    var communityTotalRecruitment: Int,
    var communityCurrentRecruitment: Int,
    var participantsList: List<SealedUser>
)