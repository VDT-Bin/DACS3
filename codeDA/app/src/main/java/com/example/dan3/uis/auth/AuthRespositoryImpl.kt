package com.example.dan3.uis.auth

import android.util.Log
import com.example.dan3.data.api.AuthApi
import com.example.dan3.data.api.loginRequest
import com.example.dan3.data.api.loginResponse
import com.example.dan3.data.api.registerRP
import com.example.dan3.data.api.registerRequest
import com.example.dan3.data.repository.AuthRepository
import com.example.dan3.uis.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRespositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun login(username: String, password: String) : Result<loginResponse>
    { return try {
            val response = authApi.login(loginRequest(username, password))
            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else Result.failure(Exception(response.message()?: "chua biet loi"))
        }catch(e : Exception){
            Log.d("login",e.message.toString())
            Result.failure(e)
        }
    }
    override suspend fun register(
        username: String,
        password: String,
        phone: String,
        email: String,
        name: String
    ): Result<registerRP> {
        return try {
            val response = authApi.register(registerRequest(name, phone, email, password, username))
            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else Result.failure(Exception(response.message()?: "chua biet loi"))
        }catch(e: Exception){
            Log.d("Register",e.message.toString())
            Result.failure(e)
        }
    }
}
