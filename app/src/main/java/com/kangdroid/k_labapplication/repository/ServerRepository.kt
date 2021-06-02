package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest

interface ServerRepository {
    // Register - Throws RuntimeException
    // catch -> ClientException -> message- > throw RuntimeException(message)
    fun registerUser(userRegisterRequest: RegisterRequest)

    // Login - Throws Runtime Exception
    fun loginUser(userLoginRequest: LoginRequest)
}
