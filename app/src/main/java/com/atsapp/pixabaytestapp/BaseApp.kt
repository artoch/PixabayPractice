package com.atsapp.pixabaytestapp

import android.app.Application
import android.content.Context
import com.atsapp.pixabaytestapp.utils.SessionManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application(){
    companion object {
        lateinit var appContext: Context
        val sessionManager by lazy { SessionManager(appContext) }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}