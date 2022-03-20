package com.abumadi.sawaapp.domain

import android.content.Context
import android.provider.SyncStateContract
import com.abumadi.sawaapp.data.source.AppRepository
import com.abumadi.sawaapp.others.Constants
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val appRepository: AppRepository) {

    fun themeCheckboxStateExecuted(key: String): Boolean {
        return appRepository.getCheckedThemeCheckboxes(key)
    }

    fun languageCheckboxStateExecuted(key: String): Boolean {
        return appRepository.getCheckedLanguageCheckboxes(key)
    }
}