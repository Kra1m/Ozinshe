package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int, // 25783
    @SerializedName("username")
    val username: String, // string123456@gmail.com
    @SerializedName("email")
    val email: String, // string123456@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("tokenType")
    val tokenType: String, // Bearer
    @SerializedName("accessToken")
    val accessToken: String // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdHJpbmcxMjM0NTZAZ21haWwuY29tIiwiaWF0IjoxNzU1MDkwMzUwLCJleHAiOjE3ODY2MjYzNTB9.3Zo6ZdRFbzv3QksV9EBbeDP1NgpsfebURilUnLtQdic2ZFzVk9b-xsTaxsCPJagem-r1S65_XMdWVhQGG8xCwA
)