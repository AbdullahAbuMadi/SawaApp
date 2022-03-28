package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.data.source.AppRepository
import javax.inject.Inject

class SplashUseCase @Inject constructor(private val appRepository: AppRepository) {

    fun appThemeExecute(): String? {
        return appRepository.getCurrentTheme()
    }
}