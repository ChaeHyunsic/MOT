package com.example.mot

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)

        // Extract tokens from response headers
        val authToken = response.header("Authorization")
        val refreshToken = response.header("Refresh")

        TokenManager.saveTokens(authToken, refreshToken)

        return response
    }
}
