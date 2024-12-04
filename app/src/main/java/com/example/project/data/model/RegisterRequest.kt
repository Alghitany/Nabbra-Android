package com.example.project.data.model

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String,
    val password_confirmation: String
)