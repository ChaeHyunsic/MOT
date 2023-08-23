package com.example.mot

object TokenManager {
    private var authToken: String? = null
    private var refreshToken: String? = null

    fun saveTokens(authToken: String?, refreshToken: String?) {
        this.authToken = authToken
        this.refreshToken = refreshToken
    }

    fun getAuthToken(): String? {
        return authToken
    }

    fun getRefreshToken(): String? {
        return refreshToken
    }
}
