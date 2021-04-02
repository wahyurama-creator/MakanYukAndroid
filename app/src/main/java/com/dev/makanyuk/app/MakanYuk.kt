package com.dev.makanyuk.app

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.dev.makanyuk.network.HttpClient

class MakanYuk : MultiDexApplication() {
    companion object {
        const val TOKEN = "PREFERENCES_TOKEN"
        const val USER = "PREFERENCES_USER"
        lateinit var mInstance: MakanYuk

        fun getApp(): MakanYuk {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    private fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreferences().edit().putString(TOKEN, token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString(TOKEN, null)
    }

    fun setUser(user: String) {
        getPreferences().edit().putString(USER, user).apply()
        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser(): String? {
        return getPreferences().getString(USER, null)
    }
}