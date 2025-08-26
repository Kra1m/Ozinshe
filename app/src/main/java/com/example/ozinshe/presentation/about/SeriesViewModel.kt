package com.example.ozinshe.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.data.model.MovieByIdResponse
import com.example.ozinshe.data.model.VideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel : ViewModel() {

    private var _videosResponse: MutableLiveData<List<VideoResponse>> = MutableLiveData()
    val videosResponse: LiveData<List<VideoResponse>> = _videosResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


    fun getSeriesById(token: String, movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.getSeriesById("Bearer $token", movieId) }
                .onSuccess{
                    _videosResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }

        }
    }

}