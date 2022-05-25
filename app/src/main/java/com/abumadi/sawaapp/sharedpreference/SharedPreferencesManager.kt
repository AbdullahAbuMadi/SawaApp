package com.abumadi.sawaapp.sharedpreference

import android.content.Context
import com.abumadi.sawaapp.data.source.CheckedInInfo
import com.abumadi.sawaapp.others.Constants
import com.google.gson.Gson

class SharedPreferencesManager {

    fun saveThemesCheckBoxState(applicationContext: Context, key: String) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.THEMES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        prefs.edit().clear().apply()
        prefs.edit().putBoolean(key, true).apply()
    }

    fun getThemesCheckBoxState(applicationContext: Context, key: String): Boolean {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.THEMES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        return prefs.getBoolean(key, false)
    }

    fun setAppTheme(
        applicationContext: Context,
        theme: String
    ) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.THEME_PREFERENCES,
                Context.MODE_PRIVATE
            )//create sp db
        prefs.edit().clear().apply()
        prefs.edit().putString(Constants.PREF_THEME_KEY, theme)
            .apply()//store >>key ,value:theme
    }

    fun getAppTheme(applicationContext: Context): String? {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.THEME_PREFERENCES,
                Context.MODE_PRIVATE
            )
        return prefs.getString(Constants.PREF_THEME_KEY, Constants.DEFAULT_THEME)
    }

    //store the current language
    fun setAppLanguage(applicationContext: Context, language: String) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.LANGUAGES_PREFERENCES,
                Context.MODE_PRIVATE
            )
        prefs.edit().clear().apply()
        prefs.edit().putString(Constants.PREF_LANGUAGE_KEY, language).apply()
    }

    fun getAppLanguage(applicationContext: Context): String? {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.LANGUAGES_PREFERENCES,
                Context.MODE_PRIVATE
            )
        return prefs.getString(Constants.PREF_LANGUAGE_KEY, Constants.DEFAULT_LANGUAGE)
    }

    fun saveLanguagesCheckBoxState(applicationContext: Context, key: String) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.LANGUAGES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        prefs.edit().clear().apply()
        prefs.edit().putBoolean(key, true).apply()
    }

    fun getLanguagesCheckBoxState(applicationContext: Context, key: String): Boolean {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.LANGUAGES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        return prefs.getBoolean(key, false)
    }


    //store the current Ui
    fun setAppCurrentUi(applicationContext: Context, ui: String) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.CURRENT_UI_PREFERENCE,
                Context.MODE_PRIVATE
            )
        prefs.edit().putString(Constants.PREF_CURRENT_UI_KEY, ui).apply()
    }

    fun getAppCurrentUi(applicationContext: Context): String? {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.CURRENT_UI_PREFERENCE,
                Context.MODE_PRIVATE
            )
        return prefs.getString(Constants.PREF_CURRENT_UI_KEY, Constants.DEFAULT_UI)
    }


    //store checked in information
    fun saveCheckedInInfo(
        applicationContext: Context,
        checkedInInfo: CheckedInInfo
    ) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.CHECKED_IN_PREFERENCE,
                Context.MODE_PRIVATE
            )

        prefs.edit().apply {
            putString(Constants.PREF_CHECKED_IN_PLACE_NAME_KEY, Gson().toJson(checkedInInfo))
        }.apply()
    }

    fun getCheckedInInfo(applicationContext: Context): CheckedInInfo? {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.CHECKED_IN_PREFERENCE,
                Context.MODE_PRIVATE
            )

        return Gson().fromJson(prefs.getString(Constants.PREF_CHECKED_IN_PLACE_NAME_KEY, null),
            CheckedInInfo::class.java)
    }
}
