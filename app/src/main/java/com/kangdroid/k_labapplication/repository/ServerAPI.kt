package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

data class Community(
    var id: Long,
    var contentTitle: String, // 제목
    var contentAuthor: String, // 만든사람
    var contentPicture: String? = null, // 사진 [없으면 null]
    var innerContent: String, // 내용
    var contentNeeds: String, // 무엇이 필요한가
    var contentDeadline: String, // 데드라인
    var firstMeeting: String, // 첫 만남
    var contentRecruitment: Int, // 인원 제한[총 정원]
    var currentRecruitment: Int, // 현재 정원
    var isCommunityExpired: Boolean, // true인 경우 모집 마감, false 인 경우 모집 진행
    var gardenReservationId: GardenReservation
)

data class MedianTable(
    var userId: Long, // [User PK]
    var communityId: Long, // [Community PK],
    var isRequestConfirmed: Boolean
)

data class GardenReservation(
    var id: Long, // 자체 Key
    var reservationTime: Long,
    var reservationSpace: String,
)

data class SimplifiedCommunity( // Community Response
    var id: Long, // Class ID
    var contentTitle: String, // 제목
    var contentRecruitment: Int, // 인원 제한[총 정원]
    var currentRecruitment: Int, // 현재 정원
    var contentNeeds: String, // 무엇이 필요한가
)

data class SimplifiedMyPageCommunity(
    var id: Long, // Class ID
    var contentTitle: String, // 제목
    var startTime: Long,
    var contentNeeds: String, // 무엇이 필요한가
    var isRequestConfirmed: Boolean
)

interface ServerAPI {
    // 회원가입 리턴은 성공/실패 정도만
    @POST("/api/v1/user")
    fun registerUser(@Body userRegisterRequest: RegisterRequest): Call<ResponseBody>

    // 로그인
    @POST("/api/v1/user/login")
    fun loginUser(@Body userLoginRequest: LoginRequest): Call<LoginResponse>

    // Get Class List[Simplified] (isCommunityExpired 가 false 인 것만)
    @GET("/api/v1/class")
    fun getClassList(@HeaderMap header: HashMap<String, Any?>): Call<List<SimplifiedCommunity>>

    // Get Detailed Class by ID
    @GET("/api/v1/class/{id}")
    fun getDetailedClass(@HeaderMap header: HashMap<String, Any?>, @Path("id") id: Long): Call<Community>

    // Join Class and return SimplifiedMyPageCommunity
    @POST("/api/v1/user/class/{id}")
    fun registerClass(@HeaderMap header: HashMap<String, Any?>, @Path("id") id: Long): Call<List<SimplifiedMyPageCommunity>>
}