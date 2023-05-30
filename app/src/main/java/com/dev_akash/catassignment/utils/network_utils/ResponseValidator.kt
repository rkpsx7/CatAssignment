package com.dev_akash.catassignment.utils.network_utils

import android.util.Log
import retrofit2.Response

fun <T> Response<T>.validate(): Resource<T?> {
    try {
        val response = this
        if (response.isSuccessful) {
            return Resource.Success(data = response.body())
        }

        if (response.code() == NO_INTERNET_CODE) {
            return Resource.Error(response.message())
        }
        if (response.code() == 403) {
            //Handle token expired case here
        }
        if (response.code() == 502) {
            //handle bad gateway case here (server possibly offline or deploying)
        }

        return Resource.Error(response.raw().code().toString())
    } catch (e: Exception) {
        return Resource.Error(e.message ?: e.toString(), null)
    }
}