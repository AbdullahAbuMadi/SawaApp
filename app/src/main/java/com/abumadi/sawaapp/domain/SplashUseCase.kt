package com.abumadi.sawaapp.domain

import androidx.lifecycle.LiveData
import com.abumadi.sawaapp.data.source.AppRepository
import javax.inject.Inject

class SplashUseCase @Inject constructor(private val appRepository: AppRepository) {

    fun getAppTheme(): LiveData<Int?> {
        return appRepository.getCurrentTheme()
    }
}