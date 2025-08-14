package com.example.ozinshe.data

import com.example.ozinshe.data.model.LoginRequest
import com.example.ozinshe.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}