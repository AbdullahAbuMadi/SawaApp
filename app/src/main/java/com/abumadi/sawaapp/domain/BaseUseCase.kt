package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.data.source.AppRepository
import javax.inject.Inject

class BaseUseCase @Inject constructor(private val appRepository: AppRepository) {

    fun appThemeExecute(): String? {
        return appRepository.getCurrentTheme()
    }

    fun appLanguageExecute(): String? {
        return appRepository.getCurrentLanguage()
    }
}