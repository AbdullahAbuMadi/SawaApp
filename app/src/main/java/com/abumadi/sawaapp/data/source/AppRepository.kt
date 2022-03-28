package com.abumadi.sawaapp.data.source

import android.content.Context
import com.abumadi.sawaapp.sharedpreference.SharedPreferencesManager
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val db: SharedPreferencesManager,
    private val context: Context
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

    fun getCheckedLanguageCheckboxes(key: String): Boolean {
        return db.getLanguagesCheckBoxState(context, key)
    }
}