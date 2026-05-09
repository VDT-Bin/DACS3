package com.example.dan3.uis.login

import com.example.dan3.core.base.AppResult
import com.example.dan3.data.api.AuthApi
import com.example.dan3.data.api.loginRequest
import com.example.dan3.data.api.loginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRespository(private val authApi: AuthApi) {
    suspend fun login( username:String, password:String) : AppResult<loginResponse> = withContext(
        Dispatchers.IO) {
        try {
            val response = authApi.login(loginRequest(username,password))
            if (response.isSuccessful && response.body() != null){
                AppResult.Success(response.body()!!)
            }else{
                AppResult.Error(response.message())
            }
        }catch (e : Exception){
            AppResult.Error(e.message?: "chua biet loi")
        }
    }

    }
