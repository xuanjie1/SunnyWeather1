package com.sunnyweather1.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather1.android.logic.model.Place
import com.sunnyweather1.android.logic.network.SunnyWeather1NetWork
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

object Respository {
    fun searchPlaces(query:String)= liveData (Dispatchers.IO){
        val result=try {
            val placeRespnse=SunnyWeather1NetWork.searchPlaces(query)
            if(placeRespnse.status=="ok"){
                val places=placeRespnse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeRespnse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}