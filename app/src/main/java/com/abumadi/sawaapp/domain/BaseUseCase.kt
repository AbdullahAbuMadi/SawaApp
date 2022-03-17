package com.abumadi.sawaapp.domain

import android.app.Activity
import androidx.lifecycle.LiveData
import com.abumadi.sawaapp.data.source.AppRepository
import javax.inject.Inject

class BaseUseCase @Inject constructor(private val appRepository: AppRepository) {

    fun getAppTheme(): LiveData<Int?> {
        return appRepository.getCurrentTheme()
    }

    fun getAppLanguage(): LiveData<String?> {
        return appRepository.getCurrentLanguage()
    }
}