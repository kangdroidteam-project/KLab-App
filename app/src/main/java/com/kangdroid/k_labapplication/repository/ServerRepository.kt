package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.Community
import com.kangdroid.k_labapplication.data.SimplifiedCommunity
import com.kangdroid.k_labapplication.data.SimplifiedMyPageCommunity
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import okhttp3.ResponseBody

interface ServerRepository {
    fun registerUser(
        userRegisterRequest: RegisterRequest,
        onSuccess: (responseBody: ResponseBody?)->Unit,
        onFailureLambda: (message: String)->Unit
    )

    fun loginUser(
        userLoginRequest: LoginRequest,
        onSuccess: (loginResponse: LoginResponse?) -> Unit,
        onFailureLambda: (message: String) -> Unit
    )

    fun getClassList(): List<SimplifiedCommunity>
    fun getDetailedClass(id: Long): Community
    fun registerClass(id: Long): List<SimplifiedMyPageCommunity>
}
