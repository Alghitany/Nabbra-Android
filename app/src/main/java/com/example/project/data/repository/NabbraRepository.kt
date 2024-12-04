package com.example.project.data.repository

import com.example.project.data.model.LoginRequest
import com.example.project.data.model.LoginResponse
import com.example.project.data.model.RegisterRequest
import com.example.project.data.model.RegisterResponse
import com.example.project.data.networking.ApiServices
import retrofit2.Response
import javax.inject.Inject


class NabbraRepository @Inject constructor(
    private val apiServices: ApiServices,
) {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiServices.login(loginRequest)
    }

    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return apiServices.register(registerRequest)
    }

}