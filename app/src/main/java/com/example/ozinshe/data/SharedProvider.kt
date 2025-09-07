package com.example.ozinshe.data

import android.content.Context
import android.content.SharedPreferences

class SharedProvider(private val context: Context) {
    private val shared_token = "SAVE_TOKEN"

    private val preference: SharedPreferences
        get() = context.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)

    fun saveToken(token: String){
        preference.edit().putString(shared_token, token).apply()
    }

    fun getToken(): String{
        return preference.getString(shared_token, "without_token").toString()
    }

    fun saveLanguage(language: String){
        preference.edit().putString("language", language).apply()
    }

    fun getLanguage(): String{
        return preference.getString("language", "kk").toString()
    }

    fun saveDayMode(mode:Boolean){
        preference.edit().putBoolean("day_night_mode", mode).apply()
    }

    fun getDayMode(): Boolean{
        return preference.getBoolean("day_night_mode", true)
    }

    fun clearShared(){
        preference.edit().clear().apply()
    }

}