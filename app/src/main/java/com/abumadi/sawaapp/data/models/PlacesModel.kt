package com.abumadi.sawaapp.data.models

data class PlacesModel(
    val placeIcon: Int?,
    val placeName: String,
    val placeFar: String,
    val isOpen: Boolean? = false,
    val availabilityDescription: String,
    val capacity: String,
    val crowdingLevel:String,
    val crowdingBackgroundColor:Int?,
    val crowdingText:String,
)