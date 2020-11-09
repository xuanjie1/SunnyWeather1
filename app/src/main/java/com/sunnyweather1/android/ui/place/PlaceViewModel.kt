package com.sunnyweather1.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather1.android.logic.Respository
import com.sunnyweather1.android.logic.model.Place

class PlaceViewModel:ViewModel() {
    private val searchLiveData=MutableLiveData<String>()
    val placeList=ArrayList<Place>()
    val placeLiveData=Transformations.switchMap(searchLiveData){
        query ->Respository.searchPlaces(query)
    }
    fun searchPlaces(query:String){
        searchLiveData.value=query
    }
}