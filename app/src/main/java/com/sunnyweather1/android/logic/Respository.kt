package com.sunnyweather1.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather1.android.logic.dao.PlaceDao
import com.sunnyweather1.android.logic.model.Place
import com.sunnyweather1.android.logic.model.Weather
import com.sunnyweather1.android.logic.network.SunnyWeather1NetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

object Respository {
    fun savePlace(place: Place)=PlaceDao.savePlace(place)
    fun getSavedPlace()=PlaceDao.getSavedPlace()
    fun isPlaceSaved()=PlaceDao.isPlaceSaved()
    fun searchPlaces(query:String)= fire (Dispatchers.IO){

            val placeRespnse=SunnyWeather1NetWork.searchPlaces(query)
            if(placeRespnse.status=="ok"){
                val places=placeRespnse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeRespnse.status}"))
            }
//        }catch (e:Exception){
//            Result.failure<List<Place>>(e)
//        }
//        emit(result)
    }
    fun refreshWeather(lng:String,lat:String)= fire(Dispatchers.IO) {
//        val result=try {
            coroutineScope {
                val deferredRealtime=async {
                    SunnyWeather1NetWork.getRealtimeWeather(lng,lat)
                }
                val deferredDaily=async {
                    SunnyWeather1NetWork.getDailyWeather(lng,lat)
                }
                val realtimeResponse=deferredRealtime.await()
                val dailyResponse=deferredDaily.await()
                if (realtimeResponse.status=="ok"&&dailyResponse.status=="ok"){
                    val weather=Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realtimeResponse.status}"+
                                    "daily response status is ${dailyResponse.status}"
                        )
                    )
                }
            }
//        }catch (e:Exception){
//            Result.failure<Weather>(e)
//        }
//        emit(result)
    }
    private fun <T> fire(context:CoroutineContext,block:suspend ()->Result<T>)=
            liveData<Result<T>>(context) {
                val result=try {
                    block()
                }catch (e:java.lang.Exception){
                    Result.failure<T>(e)
                }
                emit(result)
            }
}