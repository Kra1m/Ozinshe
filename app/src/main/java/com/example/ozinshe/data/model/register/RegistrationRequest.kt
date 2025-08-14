package com.example.ozinshe.data.model.register


import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("email")
    val email: String, // random@gmail.com
    @SerializedName("password")
    val password: String // badromance
)