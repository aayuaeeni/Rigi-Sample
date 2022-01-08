package com.raju.rigi_sample.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceProvider
constructor(
    private val context: Context
) {

    companion object {
        const val IS_LOGIN = "is_login"
    }

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun clearPreferences() {
        preference.edit().clear().apply()
    }



    fun saveIsLogin(is_login: Boolean) {
        preference.edit().putBoolean(
            IS_LOGIN,
            is_login
        ).apply()
    }

    fun getIsLogin(): Boolean {
        return preference.getBoolean(IS_LOGIN, false)
    }

}