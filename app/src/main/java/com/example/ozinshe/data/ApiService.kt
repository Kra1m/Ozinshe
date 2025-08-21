package com.example.ozinshe.data

import com.example.ozinshe.data.model.MainByIdList.MovieByIdResponse
import com.example.ozinshe.data.model.login.LoginRequest
import com.example.ozinshe.data.model.login.LoginResponse
import com.example.ozinshe.data.model.mainMovieList.MainMoviesResponse
import com.example.ozinshe.data.model.mainMovieList.MainMoviesResponseItem
import com.example.ozinshe.data.model.register.RegistrationRequest
import com.example.ozinshe.data.model.register.RegistrationResponse
import com.example.ozinshe.data.moviesCategories.MoviesByCategoryMainModel
import com.google.gson.stream.JsonToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String) : MainMoviesResponse

    @GET ("/core/V1/movies/main")
    suspend fun getMainMoviesByCategory(@Header("Authorization") token: String): MoviesByCategoryMainModel

    @GET ("/core/V1/movies/{id}")
    suspend fun getMovieById(@Header("Authorization") token: String,
                           @Path("id") id: Int): MovieByIdResponse


    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun  registerUser(@Body registrationRequest: RegistrationRequest) : RegistrationResponse
}