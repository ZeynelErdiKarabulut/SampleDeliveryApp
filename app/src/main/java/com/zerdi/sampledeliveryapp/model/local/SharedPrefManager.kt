package com.zerdi.sampledeliveryapp.model.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    companion object {
        const val TOKEN = "com.zerdi.sampledeliveryapp.model.TOKEN"
        const val IMAGE = "com.zerdi.sampledeliveryapp.model.IMAGE"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun saveImage(token: String) {
        sharedPreferences.edit().putString(IMAGE, token).apply()
    }

    fun getImage(): String? {
        return sharedPreferences.getString(IMAGE, "")
    }

    fun setOnboardingSeen() {
        sharedPreferences.edit().putBoolean("ONBOARDING", true).apply()
    }

    fun isOnboardingSeen(): Boolean {
        return sharedPreferences.getBoolean("ONBOARDING", false)
    }
}