package com.example.project.interceptor

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {

    companion object {
        private const val TOKEN_KEY = "auth_token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Retrieve the token from SharedPreferences
        val token = sharedPreferences.getString(TOKEN_KEY, null)
        token?.let {
            // Add token to the request header if it exists
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

    // Save token to SharedPreferences
    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    // Clear the saved token from SharedPreferences
    fun clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }
}
