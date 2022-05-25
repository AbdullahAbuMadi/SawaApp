package com.abumadi.sawaapp.data.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckedInInfo(
    val placeName: String,
    val placeIcon: Int,
    val branchName: String,
    val duration: String,
    val checkInTime: String
):Parcelable