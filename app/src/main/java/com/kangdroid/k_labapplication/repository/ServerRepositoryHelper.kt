package com.kangdroid.k_labapplication.repository

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ServerRepositoryHelper {
    private val logTag: String = this::class.java.simpleName
    private val objectMapper: ObjectMapper = ObjectMapper()

    fun<T> getCallback(onSuccess: () -> Unit, onFailureLambda: (message: String) -> Unit): Callback<T> {
        return object: Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                // When 200-ish response
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    // For 400 to 500 response
                    val errorMessage: String = ServerRepositoryHelper.getErrorMessage(response)
                    Log.e(this::class.java.simpleName, errorMessage)
                    onFailureLambda(errorMessage)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // somewhat fatal error[I/O]
                Log.e(this::class.java.simpleName, "Error communicating to server: ${t.stackTraceToString()}")
                onFailureLambda(t.message ?: "No Message")
            }
        }
    }

    private fun <T> getErrorMessage(response: Response<T>): String {
        // If error body is null, something went wrong.
        val errorBody: ResponseBody = response.errorBody()!!

        // Get error body as map, since spring's default error response was sent.
        val errorBodyMap: Map<String, String> = objectMapper.readValue(errorBody.string()) // Could throw
        return if (errorBodyMap.contains("message")) { // Common about our error response and spring error response
            errorBodyMap["message"]!!
        } else {
            "Error message was not found!! This should be reported to developers."
        }
    }
}