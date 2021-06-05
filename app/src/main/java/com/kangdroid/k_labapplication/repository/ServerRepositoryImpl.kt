package com.kangdroid.k_labapplication.repository

import android.util.Log
import com.kangdroid.k_labapplication.data.*
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.data.dto.response.LoginResponse
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerRepositoryImpl: ServerRepository {
    private val logTag: String = this::class.java.simpleName
    private lateinit var retroFit: Retrofit
    private lateinit var api: ServerAPI

    // Whether server connection is successful or not
    private var isServerEnabled: Boolean = false

    // User Token
    var userToken: String? = null

    // Headers
    private fun getHeaders(): HashMap<String, Any?>{
        // Set headers
        val headers : HashMap<String, Any?> = HashMap()
        headers["X-AUTH-TOKEN"] = userToken
        return headers
    }

    init {
        isServerEnabled = initWholeServerClient()
    }

    fun initWholeServerClient(): Boolean {
        var returnServerEnabled: Boolean = false
        runCatching {
            retroFit = initRetroFit()
            api = initApiInterface()
        }.onSuccess {
            returnServerEnabled = true
        }.onFailure {
            Log.e(logTag, "Error occurred when connecting to server: ${it.message}")
            Log.e(logTag, "Error Message: ${it.stackTraceToString()}")
            returnServerEnabled = false
        }
        return returnServerEnabled
    }

    private fun initRetroFit(): Retrofit {
        val httpUrl: HttpUrl = HttpUrl.Builder()
            .scheme("http")
            .host("192.168.0.46") // TODO 서버 주소
            .port(8080)
            .build()
        return Retrofit.Builder()
            .baseUrl(httpUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initApiInterface(): ServerAPI {
        return retroFit.create(ServerAPI::class.java)
    }

    override fun registerUser(
        userRegisterRequest: RegisterRequest,
        onSuccess: (responseBody: ResponseBody?)->Unit,
        onFailureLambda: (message: String)->Unit
    ) {
        val registerFunction: Call<ResponseBody> = api.registerUser(userRegisterRequest)
        registerFunction.enqueue(ServerRepositoryHelper.getCallback(onSuccess, onFailureLambda))
    }

    override fun loginUser(
        userLoginRequest: LoginRequest,
        onSuccess: (loginResponse: LoginResponse?) -> Unit,
        onFailureLambda: (message: String) -> Unit
    ) {
        val loginFunction: Call<LoginResponse> = api.loginUser(userLoginRequest)
        val customSuccess: (loginResponse: LoginResponse?) -> Unit = {
            userToken = it?.userToken
            onSuccess.invoke(it)
        }
        loginFunction.enqueue(ServerRepositoryHelper.getCallback<LoginResponse>(customSuccess, onFailureLambda))
    }

    override fun getClassList(): List<SimplifiedCommunity> {
        val getClassListFunc: Call<List<SimplifiedCommunity>> = api.getClassList(header = getHeaders())
        val response: Response<List<SimplifiedCommunity>> =
            ServerRepositoryHelper.exchangeDataWithServer(getClassListFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }

        return response.body()
            ?: throw RuntimeException("Response Error")
    }

    override fun getDetailedClass(id: Long): Community {
        val getDetailedFunc: Call<Community> =
            api.getDetailedClass(header = getHeaders(), id = id)
        val response: Response<Community> =
            ServerRepositoryHelper.exchangeDataWithServer(getDetailedFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }

        return response.body()
            ?: throw RuntimeException("Response Error")
    }

    override fun registerClass(id: Long): List<SimplifiedMyPageCommunity> {
        val registerClassFunc: Call<List<SimplifiedMyPageCommunity>>
                = api.registerClass(header = getHeaders(), id = id)
        val response: Response<List<SimplifiedMyPageCommunity>> =
            ServerRepositoryHelper.exchangeDataWithServer(registerClassFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }

        return response.body()
            ?: throw RuntimeException("Response Error")
    }

    override fun addClass(communityAddRequest: CommunityAddRequest) {
        val addClassFunc: Call<ResponseBody>
                = api.addClass(header = getHeaders(), communityAddRequest = communityAddRequest)
        val response: Response<ResponseBody> =
            ServerRepositoryHelper.exchangeDataWithServer(addClassFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }
    }

    override fun getUser(): SealedUser {
        val getUserFunc: Call<SealedUser> = api.getUser(header = getHeaders())
        val response: Response<SealedUser> =
            ServerRepositoryHelper.exchangeDataWithServer(getUserFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }

        return response.body()
            ?: throw RuntimeException("Response Error")
    }

    override fun getUserParticipatedClass(isParticipant: Boolean): List<SimplifiedMyPageCommunity> {
        val getUserParticipatedClassFunc: Call<List<SimplifiedMyPageCommunity>>
                = api.getUserParticipatedClass(header = getHeaders(), isParticipant = isParticipant)
        val response: Response<List<SimplifiedMyPageCommunity>> =
            ServerRepositoryHelper.exchangeDataWithServer(getUserParticipatedClassFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }

        return response.body()
            ?: throw RuntimeException("Response Error")
    }

    override fun getClassParticipants(id: Long): ManagerConfirmCommunity {
        val getClassParticipantsFunc: Call<ManagerConfirmCommunity> =
            api.getClassParticipants(header = getHeaders(), id = id)
        val response: Response<ManagerConfirmCommunity> =
            ServerRepositoryHelper.exchangeDataWithServer(getClassParticipantsFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }

        return response.body()
            ?: throw RuntimeException("Response Error")
    }

    override fun confirmClassParticipants(id: Long, userName: String) {
        val confirmClassParticipantsFunc: Call<ResponseBody>
                = api.confirmClassParticipants(header = getHeaders(), id = id, userName = userName)
        val response: Response<ResponseBody> =
            ServerRepositoryHelper.exchangeDataWithServer(confirmClassParticipantsFunc)

        if (!response.isSuccessful) {
            // handling fail
            Log.e(logTag, "${response.code()}")
            ServerRepositoryHelper.getErrorMessage(response)
        }
    }
}