package com.abumadi.sawaapp.data.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckedInInfo(
    val placeName: String,
    val placeIcon: String? = null,
    val branchName: String,
    val duration: Double,
    val checkInTime: String
) : Parcelable