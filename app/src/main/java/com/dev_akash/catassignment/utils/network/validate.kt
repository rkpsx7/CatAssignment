package com.dev_akash.catassignment.utils.network

import android.util.Log
import retrofit2.Response

fun <T> Response<T>.validate(): Resource<T?> {
    try {
        val response = this
        Log.d("rkpsx7rkpsx7",response.body().toString())
        if (response.isSuccessful) {
            return Resource.Success(data = response.body())
//            response.body()?.let {
//                Resource.Success(it)
//            } ?: kotlin.run {
//                Resource.Error("Empty data received")
//            }
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