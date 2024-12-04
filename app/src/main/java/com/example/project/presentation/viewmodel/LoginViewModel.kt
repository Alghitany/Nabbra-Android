package com.example.project.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.model.LoginRequest
import com.example.project.data.model.LoginResponse
import com.example.project.data.repository.NabbraRepository
import com.example.project.interceptor.AuthInterceptor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: NabbraRepository,
    private val authInterceptor: AuthInterceptor
) : ViewModel() {

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)

    // To handle errors, you can use a separate mutableStateOf
    private val _errorMessage = mutableStateOf<String?>(null)

    private val _userName = mutableStateOf<String?>(null)
    private val userName = _userName

    private val _userEmail = mutableStateOf<String?>(null)
    private val userEmail = _userEmail

    fun login(loginRequest: LoginRequest,onSuccessfulLogin: () -> Unit,onFailedLogin: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(loginRequest)
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                    _loginResponse.value?.token?.let { token ->
                        authInterceptor.saveToken(token)
                    }
                    _loginResponse.value?.data?.name?.let { name ->
                        _userName.value = name
                    }
                    _loginResponse.value?.data?.email?.let { email ->
                        _userEmail.value = email
                    }
                    if (_loginResponse.value?.token != null) {
                        onSuccessfulLogin()
                    } else {
                        onFailedLogin()
                    }
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "123Server error: ${response.code()}"
                    println(_errorMessage)
                }
            }catch (e:Exception){
                _errorMessage.value = "123Network error: ${e.localizedMessage}"
                println(_errorMessage)
            }
        }
    }
    fun getName(): String? {
        return userName.value
    }
    fun getEmail(): String? {
        return userEmail.value
    }
}