package com.abumadi.sawaapp.api.responses

data class CheckedInInfoResponse(
    val capacity: String,
    val placeBranch: String,
    val placeImage: String? = null,
    val placeName: String
)