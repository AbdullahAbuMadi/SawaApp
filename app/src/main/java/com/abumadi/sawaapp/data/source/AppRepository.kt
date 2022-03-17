package com.abumadi.sawaapp.data.source

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abumadi.sawaapp.db.SharedPreferencesDb
import javax.inject.Inject

class AppRepository @Inject constructor(private val context: Context) {

    private val _appLanguage = MutableLiveData<String>()
    private val _appTheme = MutableLiveData<Int>()

    fun getCurrentTheme(): LiveData<Int?> {
        _appTheme.value = SharedPreferencesDb.getAppTheme(context)
        return _appTheme
    }

    fun getCheckedThemeCheckboxes(key: String): Boolean {
        return SharedPreferencesDb.getLanguagesChickBoxState(context, key)
    }

    fun getCurrentLanguage(): LiveData<String?> {
        _appLanguage.value = SharedPreferencesDb.getAppLanguage(context)
        return _appLanguage
    }

    fun getCheckedLanguageCheckboxes(key: String): Boolean {
        return SharedPreferencesDb.getLanguagesChickBoxState(context, key)
    }
}