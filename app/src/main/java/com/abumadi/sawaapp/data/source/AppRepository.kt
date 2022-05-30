package com.abumadi.sawaapp.data.source

import android.content.Context
import com.abumadi.sawaapp.api.remote.ApiService
import com.abumadi.sawaapp.api.responses.CheckedInInfoResponse
import com.abumadi.sawaapp.sharedpreference.SharedPreferencesManager
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val db: SharedPreferencesManager,
    private val context: Context,
    private val apiService: ApiService
) {

    fun getCurrentTheme(): String? {
        return db.getAppTheme(context)
    }

    fun getCheckedThemeCheckboxes(key: String): Boolean {
        return db.getThemesCheckBoxState(context, key)
    }

    fun getCurrentLanguage(): String? {
        return db.getAppLanguage(context)
    }

    fun getCurrentUi(): String? {
        return db.getAppCurrentUi(context)
    }

    fun getCheckedLanguageCheckboxes(key: String): Boolean {
        return db.getLanguagesCheckBoxState(context, key)
    }

    suspend fun getCheckedInInfo(url: String): CheckedInInfoResponse {
        return apiService.getCheckedInInfo(url)
    }
}