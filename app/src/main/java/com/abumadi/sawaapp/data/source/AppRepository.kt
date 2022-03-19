package com.abumadi.sawaapp.data.source

import android.content.Context
import com.abumadi.sawaapp.db.SharedPreferencesDb
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val db: SharedPreferencesDb,
    private val context: Context
) {

    fun getCurrentTheme(): String? {
        return db.getAppTheme(context)
    }

    fun getCheckedThemeCheckboxes(key: String): Boolean {
        return db.getThemesChickBoxState(context, key)
    }

    fun getCurrentLanguage(): String? {
        return db.getAppLanguage(context)
    }

    fun getCheckedLanguageCheckboxes(key: String): Boolean {
        return db.getLanguagesChickBoxState(context, key)
    }
}