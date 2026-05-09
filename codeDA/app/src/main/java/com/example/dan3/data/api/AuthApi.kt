package com.example.dan3.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi{
    @POST("Login.php")
    suspend fun login(@Body request: loginRequest) : Response<loginResponse>

    @POST("register.php")
    suspend fun register(@Body request: registerRequest)
    }
data class registerRequest(
    val name: String,
    val phone: String,
    val email: String,
    val password: String,
    val username:String
)
data class loginRequest(
    val username: String,
    val password: String
)
data class loginResponse(
    val token: String? = null,
    val username :String? = null,
    val userId : Int? = null
)