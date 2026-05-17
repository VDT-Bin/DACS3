package com.example.dan3.uis.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dan3.data.api.loginResponse
import com.example.dan3.data.api.registerRP
import com.example.dan3.data.repository.AuthRepository
import com.example.dan3.uis.UiEvent
import com.example.dan3.uis.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class AuthState(
    val isLoading: Boolean = false,
    val loginState: UiState<loginResponse> = UiState.Loading,
    val registerState: UiState<registerRP> = UiState.Loading
)
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val respository: AuthRepository
) : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun login(user: String, pass: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(
                loginState = UiState.Loading,
                isLoading = true
            )
            respository.login(user, pass).fold(
                onSuccess = { data ->
                    _authState.value = _authState.value.copy(
                        loginState = UiState.Success(data),
                        isLoading = false
                    )
                    _uiEvent.emit(UiEvent.NavigateTo("home"))
                },
                onFailure = { ex ->
                    val mess = ex.message ?: "Đăng nhập thất bại"
                    _authState.value = _authState.value.copy(
                        loginState = UiState.Error(mess),
                        isLoading = false
                    )
                    _uiEvent.emit(UiEvent.ShowToast(mess))
                }
            )
        }
        fun register(
            username: String,
            password: String,
            phone: String,
            email: String,
            name: String
        ){
            viewModelScope.launch{
                _authState.value = _authState.value.copy(
                   isLoading = true,
                    registerState = UiState.Loading
                )

                respository.register(username = username,password = password,email = email,name = name,phone = phone).fold(
                    onSuccess ={ data ->
                        _authState.value = _authState.value.copy(
                            isLoading = false,
                           registerState = UiState.Success(data)
                        )
                        _uiEvent.emit(UiEvent.NavigateTo("login"))
                    },
                    onFailure = {
                        ex->
                        val mess = ex.message ?: "Không tạo tài khoản thành công"
                        _authState.value = _authState.value.copy(
                            isLoading = false,
                            registerState = UiState.Error(mess)
                        )
                        _uiEvent.emit(UiEvent.ShowToast(mess))
                    }
                )
            }
        }
    }
}