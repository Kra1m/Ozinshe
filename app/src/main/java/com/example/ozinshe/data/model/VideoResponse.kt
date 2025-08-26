package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: Int, // 137
    @SerializedName("movieId")
    val movieId: Int, // 122
    @SerializedName("number")
    val number: Int, // 1
    @SerializedName("videos")
    val videos: List<Video>
)