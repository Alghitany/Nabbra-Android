package com.example.project.data.networking

import com.example.project.data.model.LoginRequest
import com.example.project.data.model.LoginResponse
import com.example.project.data.model.RegisterRequest
import com.example.project.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register (@Body registerRequest: RegisterRequest): Response<RegisterResponse>

}