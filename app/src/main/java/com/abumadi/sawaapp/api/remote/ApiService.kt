package com.abumadi.sawaapp.api.remote

import com.abumadi.sawaapp.api.responses.CheckedInInfoResponse
import retrofit2.http.*

interface ApiService {

    @GET("{url}")
    suspend fun getCheckedInInfo(@Path("url") url: String): CheckedInInfoResponse
}