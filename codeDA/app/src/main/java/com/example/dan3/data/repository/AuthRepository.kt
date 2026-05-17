package com.example.dan3.data.repository

import com.example.dan3.data.api.loginResponse
import com.example.dan3.data.api.registerRP
import com.example.dan3.uis.UiState

interface AuthRepository {
    suspend fun login(username:String, password:String) : Result<loginResponse>
    suspend fun register(username:String,password:String
                         ,phone:String,email:String,name:String) : Result<registerRP>
}