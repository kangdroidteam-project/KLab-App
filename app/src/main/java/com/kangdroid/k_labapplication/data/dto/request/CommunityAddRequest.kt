package com.kangdroid.k_labapplication.data.dto.request

class CommunityAddRequest (
    var contentTitle: String, // 제목
    var contentAuthor: String, // 만든사람
    var contentPicture: String? = null, // 사진 [없으면 null]
    var innerContent: String, // 내용
    var contentNeeds: String, // 무엇이 필요한가
    var contentDeadline: String, // 데드라인
    var contentRecruitment: Int, // 인원 제한[총 정원]
    var gardenReservationRequest: GardenReservationRequest // 정원 예약
    )