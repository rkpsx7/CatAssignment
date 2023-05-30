package com.dev_akash.catassignment.utils.network_utils

import com.dev_akash.catassignment.utils.Constants.AUTH_TOKEN
import com.dev_akash.catassignment.utils.SharedPrefs
import javax.inject.Inject

class AuthTokenManager @Inject constructor() {

    fun saveToken(token: String) {
        SharedPrefs.setStringAsync(AUTH_TOKEN, token)
    }

    fun getToken(): String? {
        return SharedPrefs.getStringParam(AUTH_TOKEN, null)
    }
}