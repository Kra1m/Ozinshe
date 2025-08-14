package com.example.ozinshe.data.model.register


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("id")
    val id: Int, // 25954
    @SerializedName("username")
    val username: String, // random@gmail.com
    @SerializedName("email")
    val email: String, // random@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("tokenType")
    val tokenType: String, // Bearer
    @SerializedName("accessToken")
    val accessToken: String // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYW5kb21AZ21haWwuY29tIiwiaWF0IjoxNzU1MTc1MzY5LCJleHAiOjE3ODY3MTEzNjl9.i3IFQUn9GMMkbCjTk7ngT1EOIC6S62zZKDUO5qgr1B8SRYP-nlB6pzS843BIC9AReS_h6kveQij2OYWpqgiu9w
)