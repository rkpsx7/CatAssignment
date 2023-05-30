package com.dev_akash.catassignment.utils.network_utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: AuthTokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder()
        req.addHeader("x-api-key", "${tokenManager.getToken()}")
        return chain.proceed(req.build())
    }
}