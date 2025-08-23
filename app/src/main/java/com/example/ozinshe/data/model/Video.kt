package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: Int, // 365
    @SerializedName("link")
    val link: String, // Kq0dkn0W0jE
    @SerializedName("seasonId")
    val seasonId: Any, // null
    @SerializedName("number")
    val number: Int // 0
)