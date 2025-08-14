package com.example.ozinshe.presentation.registeration

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.data.model.register.RegistrationRequest
import com.example.ozinshe.data.model.register.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(): ViewModel() {

    private var _signupResponse: MutableLiveData<RegistrationResponse> = MutableLiveData()
    var signupResponse: LiveData<RegistrationResponse> = _signupResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse


    fun registerUser(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            runCatching { response.registerUser(RegistrationRequest(email, password)) }
                .onSuccess{
                    _signupResponse.postValue(it)
                }
                .onFailure{
                    _errorResponse.postValue(it.message)
                }
        }
    }

}