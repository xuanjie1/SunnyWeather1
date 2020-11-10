package com.sunnyweather1.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather1.android.logic.Respository
import com.sunnyweather1.android.logic.model.Location

class WeatherViewModel: ViewModel() {
    private val locationLiveData=MutableLiveData<Location>()
    var locationLng= ""
    var locationLat= ""
    var placeName= ""
    val weatherLiveData=Transformations.switchMap(locationLiveData){location->
        Respository.refreshWeather(location.lng,location.lat)
    }
    fun refreshWeather(lng:String,lat:String){
        locationLiveData.value=Location(lng,lat)
    }
}