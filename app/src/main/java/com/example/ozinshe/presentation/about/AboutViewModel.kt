package com.example.ozinshe.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.data.model.MainByIdList.MovieByIdResponse
import com.example.ozinshe.data.model.mainMovieList.MainMoviesResponseItem
import com.example.ozinshe.data.moviesCategories.MoviesByCategoryMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel: ViewModel() {

    private var _moviesByIdResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIdResponse: LiveData<MovieByIdResponse> = _moviesByIdResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


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

}