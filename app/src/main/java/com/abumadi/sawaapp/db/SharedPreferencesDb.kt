package com.abumadi.sawaapp.db

import android.content.Context
import com.abumadi.sawaapp.others.Constants

class SharedPreferencesDb {

    companion object {
        fun saveThemesChickBoxState(context: Context, key: String) {
            val prefs =
                context.getSharedPreferences(
                    Constants.THEMES_CHECKBOX_PREFERENCES,
                    Context.MODE_PRIVATE
                )
            prefs.edit().clear().apply()
            prefs.edit().putBoolean(key, true).apply()
        }

        fun getThemesChickBoxState(context: Context, key: String): Boolean {
            val prefs =
                context.getSharedPreferences(
                    Constants.THEMES_CHECKBOX_PREFERENCES,
                    Context.MODE_PRIVATE
                )
            return prefs.getBoolean(key, false)
        }

        fun setAppTheme(
            context: Context,
            theme: Int
        ) {
            val prefs =
                context.getSharedPreferences(
                    Constants.THEME_PREFERENCES,
                    Context.MODE_PRIVATE
                )//create sp db
            prefs.edit().clear().apply()
            prefs.edit().putInt(Constants.PREF_THEME_KEY, theme)
                .apply()//store >>key ,value:theme
        }

        fun getAppTheme(context: Context): Int {
            val prefs =
                context.getSharedPreferences(Constants.THEME_PREFERENCES, Context.MODE_PRIVATE)
            return prefs.getInt(Constants.PREF_THEME_KEY, Constants.DEFAULT_THEME)
        }

        //store the current language
        fun setAppLanguage(context: Context, language: String) {
            val prefs =
                context.getSharedPreferences(Constants.LANGUAGES_PREFERENCES, Context.MODE_PRIVATE)
            prefs.edit().clear().apply()
            prefs.edit().putString(Constants.PREF_LANGUAGE_KEY, language).apply()
        }

        fun getAppLanguage(context: Context): String? {
            val prefs =
                context.getSharedPreferences(Constants.LANGUAGES_PREFERENCES, Context.MODE_PRIVATE)
            return prefs.getString(Constants.PREF_LANGUAGE_KEY, Constants.DEFAULT_LANGUAGE)
        }

        fun saveLanguagesChickBoxState(context: Context, key: String) {
            val prefs =
                context.getSharedPreferences(
                    Constants.LANGUAGES_CHECKBOX_PREFERENCES,
                    Context.MODE_PRIVATE
                )
            prefs.edit().clear().apply()
            prefs.edit().putBoolean(key, true).apply()
        }

        fun getLanguagesChickBoxState(context: Context, key: String): Boolean {
            val prefs =
                context.getSharedPreferences(
                    Constants.LANGUAGES_CHECKBOX_PREFERENCES,
                    Context.MODE_PRIVATE
                )
            return prefs.getBoolean(key, false)
        }
    }
}