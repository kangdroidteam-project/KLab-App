package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.dto.request.ClassDetailRequest
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.ClassDetailResponse
import com.kangdroid.k_labapplication.data.dto.response.ClassResponse
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import com.kangdroid.k_labapplication.data.dto.response.RegisterResponse

interface ServerInterface {
    // Login
    fun login(loginRequest: LoginRequest): LoginResponse

    // Register
    fun register(registerRequest: RegisterRequest): RegisterResponse

    // Show Class List
    fun showClassList(userToken: String):  List<ClassResponse>

    // Show Class Details
    fun requestClassDetails(classDetailRequest: ClassDetailRequest, userToken: String): ClassDetailResponse
}