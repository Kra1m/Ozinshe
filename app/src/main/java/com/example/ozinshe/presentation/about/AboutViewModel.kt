package com.example.ozinshe.presentation.about

import android.app.Service
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.data.model.MainMoviesResponseItem
import com.example.ozinshe.data.model.MovieByIdResponse
import com.example.ozinshe.data.model.MovieIdModel
import com.example.ozinshe.data.model.MoviesByCategoryMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel: ViewModel() {
    private var _favState: MutableLiveData<Boolean> = MutableLiveData()
    val favState: LiveData<Boolean> = _favState

    private var _moviesByIdResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIdResponse: LiveData<MovieByIdResponse> = _moviesByIdResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    private val _moviesAddedFavouriteResponse: MutableLiveData<MovieIdModel> = MutableLiveData()
    val moviesAddedFavouriteResponse: LiveData<MovieIdModel> = _moviesAddedFavouriteResponse

    private val _moviesDeletedFavouriteResponse: MutableLiveData<String> = MutableLiveData()
    val moviesDeletedFavouriteResponse: LiveData<String> = _moviesDeletedFavouriteResponse

    fun getMovieById(token: String, movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getMovieById("Bearer $token", movieId) }
                .onSuccess{
                    _moviesByIdResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }

        }
    }

    fun addFavorite(token: String, movieId: MovieIdModel){
        viewModelScope.launch(Dispatchers.IO){
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.addFavorite("Bearer $token", movieId) }
                .onSuccess {
                    _favState.postValue(true)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun deleteFavorite(token: String, movieId: MovieIdModel){
        viewModelScope.launch(Dispatchers.IO){
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.deleteFavorite("Bearer $token", movieId) }
                .onSuccess {
                    _favState.postValue(false)
                }
                .onFailure{
                    _errorResponse.postValue(it.message)
                }
        }
    }

}