package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.api.responses.CheckedInInfoResponse
import com.abumadi.sawaapp.data.source.AppRepository
import javax.inject.Inject

class ScannerUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend fun checkedInInfoExecute(url: String): CheckedInInfoResponse {
        return appRepository.getCheckedInInfo(url)
    }
}