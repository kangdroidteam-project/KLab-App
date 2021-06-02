package com.kangdroid.k_labapplication.repository

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

object ServerRepositoryHelper {
    private val logTag: String = this::class.java.simpleName
    private val objectMapper: ObjectMapper = ObjectMapper()

    /**
     * reified T exchangeDataWithServer(apiFunction: Call<T>): Response<T>
     * Exchange data with server.
     *
     * apiFunction: APIInterface function to execute
     * returns: Response of data.
     * Throws: Exception when network failed. (* Only return RuntimeException)
     */
    fun <T> exchangeDataWithServer(apiFunction: Call<T>): Response<T> {
        return runCatching {
            apiFunction.execute()
        }.getOrElse {
            Log.e(logTag, "Error when getting root token from server.")
            Log.e(logTag, it.stackTraceToString())
            throw it
        }
    }

    /**
     * fun <reified T> handleDataError(response: Response<T>)
     *
     * handle response error if needed[i.e error response]
     */
    fun <T> handleDataError(response: Response<T>) {
        // If error body is null, something went wrong.
        val errorBody: ResponseBody = response.errorBody()!!

        // Get error body as map, since spring's default error response was sent.
        val errorBodyMap: Map<String, String> = objectMapper.readValue(errorBody.string()) // Could throw
        if (errorBodyMap.contains("message")) { // Common about our error response and spring error response
            throw RuntimeException("Server responded with: ${errorBodyMap["message"]}")
        } else {
            throw NoSuchFieldException("Error message was not found!! This should be reported to developers.")
        }
    }

    fun <T> getErrorMessage(response: Response<T>): String {
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