package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.Community
import com.kangdroid.k_labapplication.data.GardenReservation
import com.kangdroid.k_labapplication.data.SimplifiedCommunity
import com.kangdroid.k_labapplication.data.SimplifiedMyPageCommunity
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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