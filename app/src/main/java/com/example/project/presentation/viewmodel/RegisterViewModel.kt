package com.example.project.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.model.RegisterResponse
import com.example.project.data.model.RegisterRequest
import com.example.project.data.repository.NabbraRepository
import com.example.project.interceptor.AuthInterceptor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: NabbraRepository
    , private val authInterceptor: AuthInterceptor
) :  ViewModel() {
    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    // handel error message
    private val _errorMessage = mutableStateOf<String?>(null)

    fun register (registerRequest: RegisterRequest
                  , onSuccessfulRegister: () -> Unit
                  , onFailedRegister: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.register(registerRequest)
                if (response.isSuccessful){
                    _registerResponse.value = response.body()
                    _registerResponse.value?.token?.let { token ->
                        authInterceptor.saveToken(token)
                    }
                    if (_registerResponse.value?.token != null) {
                        onSuccessfulRegister()
                    } else {
                        onFailedRegister()
                    }
                    _errorMessage.value = null
                }else{
                    _errorMessage.value = "Error Server: ${response.code()}"
                    println(_errorMessage)
                }
            }catch (e:Exception){
                _errorMessage.value = "Error Network: ${e.localizedMessage}"
                println(_errorMessage)
            }
        }
    }

}