package com.example.project.presentation.view.reusable

// Email validation
fun  isValidEmail (email : String): Boolean{
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}

// Password validation
fun isValidPassword(password: String): Boolean {
    val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W)[A-Za-z\\d\\W]{8,}$"
    return password.matches(passwordPattern.toRegex())
}