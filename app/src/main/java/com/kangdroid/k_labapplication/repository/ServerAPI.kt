package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerAPI {
    // 회원가입 리턴은 성공/실패 정도만
    @POST("/api/v1/user")
    fun registerUser(@Body userRegisterRequest: RegisterRequest): Call<ResponseBody>

    // 로그인
    @POST("/api/v1/user/login")
    fun loginUser(@Body userLoginRequest: LoginRequest): Call<LoginResponse>
}