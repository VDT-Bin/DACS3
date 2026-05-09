package com.example.dan3.uis.login

import com.example.dan3.core.base.BaseViewModel
import com.example.dan3.data.api.loginResponse
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel (private val loginRespository: LoginRespository) : BaseViewModel(){
    private val _loginState = MutableStateFlow<loginResponse>
    val loginState : StateFlow<loginResponse> = _loginState.asStateFlow()


}