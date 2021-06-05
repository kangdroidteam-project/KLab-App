package com.kangdroid.k_labapplication.repository

import com.kangdroid.k_labapplication.data.*
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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
    fun addClass(communityAddRequest: CommunityAddRequest)

    fun getUser(): SealedUser
    fun getUserParticipatedClass(isParticipant: Boolean): List<SimplifiedMyPageCommunity>
    fun getClassParticipants(id: Long): ManagerConfirmCommunity
    fun confirmClassParticipants(id: Long, userName: String)
}
