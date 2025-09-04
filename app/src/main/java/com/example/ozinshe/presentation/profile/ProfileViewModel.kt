package com.example.ozinshe.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {

    private var _language: MutableLiveData<String> = MutableLiveData()
    val language: LiveData<String> = _language

    fun selectLanguage(language: String){
        _language.postValue(language)
    }
}