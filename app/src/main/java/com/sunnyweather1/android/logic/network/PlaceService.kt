package com.sunnyweather1.android.logic.network

import com.sunnyweather1.android.SunnyWeatherApplication
import com.sunnyweather1.android.logic.model.PlaceRespnse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")

    fun searchPlaces(@Query("query") query:String): Call<PlaceRespnse>
}