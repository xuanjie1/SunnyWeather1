package com.sunnyweather1.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceRespnse(val status:String,val places:List<Place>)
data class Place(val name:String,val location:Location,
                       @SerializedName("formatted_Address") val address:String)
data class Location(val lng:String,val lat:String)