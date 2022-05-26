package com.abumadi.sawaapp.sharedpreference

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.abumadi.sawaapp.data.source.CheckedInInfo
import com.abumadi.sawaapp.others.Constants
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*


const val FAKE_THEME = "pink"
const val FAKE_LANGUAGE = "eng"
const val FAKE_Ui = "button"
const val FAKE_THEME_CHECKBOX_KEY = "theme key"
const val FAKE_LANGUAGE_CHECKBOX_KEY = "language key"
val FAKE_CHECKED_INFO = CheckedInInfo("test", 0, "test", "test", "test")


const val FAKE_DEFAULT_THEME = Constants.DEFAULT_THEME
const val FAKE_DEFAULT_LANGUAGE = Constants.DEFAULT_LANGUAGE
const val FAKE_DEFAULT_Ui = Constants.DEFAULT_UI

@RunWith(RobolectricTestRunner::class)
class SharedPreferencesManagerTest {

    private lateinit var sharedPreferencesDb: SharedPreferencesManager
    private lateinit var context: Context

    @Before
    fun setUp() {
        context =
            getApplicationContext()//or InstrumentationRegistry.getInstrumentation().targetContext//for tests
        sharedPreferencesDb = SharedPreferencesManager()
    }

    //testcases
    //1-setAppTheme
    //if theme saved successfully_get theme result will be same theme saved
    @Test
    fun setAppTheme_ifThemeSavedSuccessfully_getThemeResultWillBeSameThemeSaved() {
        sharedPreferencesDb.setAppTheme(context, FAKE_THEME)
        val result = sharedPreferencesDb.getAppTheme(context)
        assertEquals(result, FAKE_THEME)

    }

    //if theme saved failed theme result will not be same theme saved
    @Test
    fun setAppTheme_ifThemeSavedFailed_getThemeResultWillNotBeSameThemeSavedBefore() {
        sharedPreferencesDb.setAppTheme(context, "")
        val result = sharedPreferencesDb.getAppTheme(context)
        assertNotEquals(result, FAKE_THEME)
    }

    //if no theme saved AtFirstTimeAppLaunching_get theme result will be default theme
    @Test
    fun setAppTheme_ifNoThemeSavedAtFirstTimeAppLaunching_getThemeResultWillBeDefaultTheme() {
        val result = sharedPreferencesDb.getAppTheme(context)
        assertEquals(result, FAKE_DEFAULT_THEME)
    }

    //2-setAppLanguage
    //if language saved successfully_get language result will be same language saved
    @Test
    fun setAppLanguage_ifLanguageSavedSuccessfully_getLanguageResultWillBeSameLanguageSaved() {
        sharedPreferencesDb.setAppLanguage(context, FAKE_LANGUAGE)
        val result = sharedPreferencesDb.getAppLanguage(context)
        assertEquals(result, FAKE_LANGUAGE)
    }

    //if language saved failed language result will not be same language saved
    @Test
    fun setAppLanguage_ifLanguageSavedFailed_getLanguageResultWillNotBeSameLanguageSavedBefore() {
        sharedPreferencesDb.setAppLanguage(context, "")
        val result = sharedPreferencesDb.getAppLanguage(context)
        assertNotEquals(result, FAKE_LANGUAGE)
    }

    //if no language saved _get language result will be default language
    @Test
    fun setAppLanguage_ifNoLanguageSavedAtFirstTimeAppLaunching_getLanguageResultWillBeDefaultLanguage() {
        val result = sharedPreferencesDb.getAppLanguage(context)
        assertEquals(result, FAKE_DEFAULT_LANGUAGE)
    }

    //3-saveThemesChickBoxState
    //if theme checkbox state saved successfully_get theme checkbox state result will be true
    @Test
    fun saveThemesCheckBoxState_ifThemeCheckboxSavedSuccessfully_getThemeCheckboxStateResultWillBeTrue() {
        sharedPreferencesDb.saveThemesCheckBoxState(context, FAKE_THEME_CHECKBOX_KEY)
        val result = sharedPreferencesDb.getThemesCheckBoxState(context, FAKE_THEME_CHECKBOX_KEY)
        assertEquals(result, true)
    }

    //if theme checkbox state saved failed_get theme checkbox state result will not be true
    @Test
    fun saveThemesCheckBoxState_ifThemeCheckboxSavedFailed_getThemeCheckboxStateResultWillBeTrue() {
        sharedPreferencesDb.saveThemesCheckBoxState(context, "")
        val result = sharedPreferencesDb.getThemesCheckBoxState(context, FAKE_THEME_CHECKBOX_KEY)
        assertEquals(result, false)
    }

    //if no theme checkbox state saved _get theme checkbox state result will be default theme checkbox state
    @Test
    fun saveThemesCheckBoxState_ifNoCheckboxesCheckedAtFirstTimeAppLaunching_getThemeCheckboxStateResultWillBeDefault_false() {
        val result = sharedPreferencesDb.getThemesCheckBoxState(context, "")
        assertEquals(result, false)
    }

    //4-saveLanguagesCheckBoxState
    //if language checkbox state saved successfully_get language checkbox state result will be same language checkbox state saved
    @Test
    fun saveLanguagesCheckBoxState_ifLanguageCheckboxSavedSuccessfully_getLanguageCheckboxStateResultWillBeTrue() {
        sharedPreferencesDb.saveLanguagesCheckBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        val result =
            sharedPreferencesDb.getLanguagesCheckBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        assertEquals(result, true)
    }

    //if language checkbox state saved failed_get language checkbox state result will be false
    @Test
    fun saveLanguagesCheckBoxState_ifLanguageCheckboxSavedFailed_getLanguageCheckboxStateResultWillBeFalse() {
        sharedPreferencesDb.saveLanguagesCheckBoxState(context, "")
        val result =
            sharedPreferencesDb.getLanguagesCheckBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        assertEquals(result, false)
    }

    //if no language checkbox state saved _get language checkbox state result will be default language checkbox state
    @Test
    fun saveLanguagesCheckBoxState_ifNoCheckboxesCheckedAtFirstTimeAppLaunching_getLanguageCheckboxStateResultWillBeDefault_false() {
        val result =
            sharedPreferencesDb.getLanguagesCheckBoxState(context, FAKE_LANGUAGE_CHECKBOX_KEY)
        assertEquals(result, false)
    }

    //5-setAppCurrentUi
    //if ui saved successfully_get ui result will be same ui saved
    @Test
    fun setAppCurrentUi_ifUiSavedSuccessfully_getUiResultWillBeSameUiSaved() {
        sharedPreferencesDb.setAppCurrentUi(context, FAKE_Ui)
        val result = sharedPreferencesDb.getAppCurrentUi(context)
        assertEquals(FAKE_Ui, result)
    }

    //if ui saved failed ui result will not be same ui saved
    @Test
    fun setAppCurrentUi_ifUiSavedFailed_getUiResultWillNotBeSameUiSavedBefore() {
        sharedPreferencesDb.setAppCurrentUi(context, "")
        val result = sharedPreferencesDb.getAppCurrentUi(context)
        assertNotEquals(FAKE_Ui, result)
    }

    //if no ui saved _get ui result will be default ui
    @Test
    fun setAppCurrentUi_ifNoUiSavedAtFirstTimeAppLaunching_getUiResultWillBeDefaultUi() {
        val result = sharedPreferencesDb.getAppCurrentUi(context)
        assertEquals(FAKE_DEFAULT_Ui, result)
    }

    //6-saveCheckedInInfo
    //if CheckedInInfo object saved successfully_get object result will be same object saved
    @Test
    fun saveCheckedInInfo_ifCheckedInInfoObjectSavedSuccessfully_getObjectResultWillBeSameObject() {
        sharedPreferencesDb.saveCheckedInInfo(context, FAKE_CHECKED_INFO)
        val result = sharedPreferencesDb.getCheckedInInfo(context)
        assertEquals(FAKE_CHECKED_INFO, result)
    }
}