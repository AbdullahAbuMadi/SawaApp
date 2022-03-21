package com.abumadi.sawaapp.db

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.abumadi.sawaapp.others.Constants
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

const val FAKE_THEME = "pink"
const val FAKE_LANGUAGE = "eng"
const val FAKE_THEME_CHECKBOX_KEY = "theme key"
const val FAKE_LANGUAGE_CHECKBOX_KEY = "language key"


const val FAKE_DEFAULT_THEME = Constants.DEFAULT_THEME
const val FAKE_DEFAULT_LANGUAGE = Constants.DEFAULT_LANGUAGE

@RunWith(RobolectricTestRunner::class)
class SharedPreferencesDbTest {

    private lateinit var sharedPreferencesDb: SharedPreferencesDb
    private lateinit var context: Context

    @Before
    fun setUp() {
        context =
            getApplicationContext()//or InstrumentationRegistry.getInstrumentation().targetContext//for tests
        sharedPreferencesDb = SharedPreferencesDb()

    }

    //testcases
    //1-setAppTheme
    //if theme saved successfully_get theme result will be same theme saved
    @Test
    fun setAppTheme_ifThemeSavedSuccessfully_getThemeResultWillBeSameThemeSaved() {
        sharedPreferencesDb.setAppTheme(context, FAKE_THEME)
        val result = sharedPreferencesDb.getAppTheme(context)
        assertThat(result).isEqualTo(FAKE_THEME)
    }

    //if theme saved failed theme result will not be same theme saved
    @Test
    fun setAppTheme_ifThemeSavedFailed_getThemeResultWillNotBeSameThemeSavedBefore() {
        sharedPreferencesDb.setAppTheme(context, "")
        val result = sharedPreferencesDb.getAppTheme(context)
        assertThat(result).isNotEqualTo(FAKE_THEME)
    }

    //if no theme saved AtFirstTimeAppLaunching_get theme result will be default theme
    @Test
    fun setAppTheme_ifNoThemeSavedAtFirstTimeAppLaunching_getThemeResultWillBeDefaultTheme() {
        val result = sharedPreferencesDb.getAppTheme(context)
        assertThat(result).isEqualTo(FAKE_DEFAULT_THEME)
    }

    //2-setAppLanguage
    //if language saved successfully_get language result will be same language saved
    @Test
    fun setAppLanguage_ifLanguageSavedSuccessfully_getLanguageResultWillBeSameLanguageSaved() {
        sharedPreferencesDb.setAppLanguage(context, FAKE_LANGUAGE)
        val result = sharedPreferencesDb.getAppLanguage(context)
        assertThat(result).isEqualTo(FAKE_LANGUAGE)
    }

    //if language saved failed language result will not be same language saved
    @Test
    fun setAppLanguage_ifLanguageSavedFailed_getLanguageResultWillNotBeSameLanguageSavedBefore() {
        sharedPreferencesDb.setAppLanguage(context, "")
        val result = sharedPreferencesDb.getAppLanguage(context)
        assertThat(result).isNotEqualTo(FAKE_LANGUAGE)
    }

    //if no language saved _get language result will be default language
    @Test
    fun setAppLanguage_ifNoLanguageSavedAtFirstTimeAppLaunching_getLanguageResultWillBeDefaultLanguage() {
        val result = sharedPreferencesDb.getAppLanguage(context)
        assertThat(result).isEqualTo(FAKE_DEFAULT_LANGUAGE)
    }

    //3-saveThemesChickBoxState
    //if theme checkbox state saved successfully_get theme checkbox state result will be true
    @Test
    fun saveThemesChickBoxState_ifThemeCheckboxSavedSuccessfully_getThemeCheckboxStateResultWillBeTrue() {
        sharedPreferencesDb.saveThemesChickBoxState(context, FAKE_THEME_CHECKBOX_KEY)
        val result = sharedPreferencesDb.getThemesChickBoxState(context, FAKE_THEME_CHECKBOX_KEY)
        assertThat(result).isTrue()
    }

    //if theme checkbox state saved failed_get theme checkbox state result will not be true
    @Test
    fun saveThemesChickBoxState_ifThemeCheckboxSavedFailed_getThemeCheckboxStateResultWillBeTrue() {
        sharedPreferencesDb.saveThemesChickBoxState(context, "")
        val result = sharedPreferencesDb.getThemesChickBoxState(context, FAKE_THEME_CHECKBOX_KEY)
        assertThat(result).isFalse()
    }

    //if no theme checkbox state saved _get theme checkbox state result will be default theme checkbox state
    @Test
    fun saveThemesChickBoxState_ifNoCheckboxesCheckedAtFirstTimeAppLaunching_getThemeCheckboxStateResultWillBeDefault_false() {
        val result = sharedPreferencesDb.getThemesChickBoxState(context,"")
        assertThat(result).isFalse()
    }

    //4-saveLanguagesChickBoxState
    //if language checkbox state saved successfully_get language checkbox state result will be same language checkbox state saved
    @Test
    fun saveLanguagesChickBoxState_ifLanguageCheckboxSavedSuccessfully_getLanguageCheckboxStateResultWillBeTrue() {
        sharedPreferencesDb.saveLanguagesChickBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        val result = sharedPreferencesDb.getLanguagesChickBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        assertThat(result).isTrue()
    }
    //if language checkbox state saved failed_get language checkbox state result will be false
    @Test
    fun saveLanguagesChickBoxState_ifLanguageCheckboxSavedFailed_getLanguageCheckboxStateResultWillBeFalse() {
        sharedPreferencesDb.saveLanguagesChickBoxState(context, "")
        val result = sharedPreferencesDb.getLanguagesChickBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        assertThat(result).isFalse()
    }
    //if no language checkbox state saved _get language checkbox state result will be default language checkbox state
    @Test
    fun saveLanguagesChickBoxState_ifNoCheckboxesCheckedAtFirstTimeAppLaunching_getLanguageCheckboxStateResultWillBeDefault_false() {
        val result = sharedPreferencesDb.getLanguagesChickBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        assertThat(result).isFalse()
    }
}