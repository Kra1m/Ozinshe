package com.example.ozinshe.presentation.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.data.model.login.LoginRequest
import com.example.ozinshe.data.model.login.LoginResponse
import com.example.ozinshe.data.model.mainMovieList.MainMoviesResponse
import com.example.ozinshe.data.moviesCategories.MoviesByCategoryMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private var _mainMoviesResponse: MutableLiveData<MainMoviesResponse> = MutableLiveData()
    val mainMoviesResponse: LiveData<MainMoviesResponse> = _mainMoviesResponse


    private var _moviesByCategoryMainModel: MutableLiveData<MoviesByCategoryMainModel> = MutableLiveData()
    val moviesByCategoryMainModel: LiveData<MoviesByCategoryMainModel> = _moviesByCategoryMainModel

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


    fun getMainMovies(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMainMovies("Bearer $token") }
                .onSuccess{
                    _mainMoviesResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }

        }
    }

    fun getMoviesByCategoryMain(token: String){
        viewModelScope.launch (Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMainMoviesByCategory("Bearer $token") }
                .onSuccess {
                    _moviesByCategoryMainModel.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

}