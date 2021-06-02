package com.kangdroid.k_labapplication.repository

import android.util.Log
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
            .host("192.168.00.00") // TODO 서버 주소
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

    override fun registerUser(userRegisterRequest: RegisterRequest) {
        val registerFunction: Call<ResponseBody> = api.registerUser(
            userRegisterRequest = userRegisterRequest)

        // Get response
        val response: Response<ResponseBody> =
            ServerRepositoryHelper.exchangeDataWithServer(registerFunction)
        if(!response.isSuccessful){
            // handle error
            ServerRepositoryHelper.handleDataError(response)
        }
    }

    override fun loginUser(userLoginRequest: LoginRequest) {
        val loginFunction: Call<LoginResponse> = api.loginUser(
            userLoginRequest = userLoginRequest)

        // Get response

        val response: Response<LoginResponse> =
            ServerRepositoryHelper.exchangeDataWithServer(loginFunction)
        if(response.isSuccessful){
            userToken = response.body()?.userToken
            Log.e(logTag, "$userToken")
        } else {
            // handle error
            ServerRepositoryHelper.handleDataError(response)
        }
    }
}