package com.abumadi.sawaapp.db

import android.content.Context
import com.abumadi.sawaapp.others.Constants

class SharedPreferencesDb {

    fun saveThemesChickBoxState(applicationContext: Context, key: String) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.THEMES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        prefs.edit().clear().apply()
        prefs.edit().putBoolean(key, true).apply()
    }

    fun getThemesChickBoxState(applicationContext: Context, key: String): Boolean {
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

    fun saveLanguagesChickBoxState(applicationContext: Context, key: String) {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.LANGUAGES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        prefs.edit().clear().apply()
        prefs.edit().putBoolean(key, true).apply()
    }

    fun getLanguagesChickBoxState(applicationContext: Context, key: String): Boolean {
        val prefs =
            applicationContext.getSharedPreferences(
                Constants.LANGUAGES_CHECKBOX_PREFERENCES,
                Context.MODE_PRIVATE
            )
        return prefs.getBoolean(key, false)
    }
}
