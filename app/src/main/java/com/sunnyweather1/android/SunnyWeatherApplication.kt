package com.sunnyweather1.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication:Application() {
    companion object{
        const val TOKEN="6j5vhjf4vm1DMThX"
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}