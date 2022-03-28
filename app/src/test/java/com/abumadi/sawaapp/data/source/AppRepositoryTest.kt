package com.abumadi.sawaapp.data.source

import android.content.Context
import com.abumadi.sawaapp.sharedpreference.SharedPreferencesManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

const val SUCCESS_THEME = "success theme"
const val SUCCESS_THEME_DEFAULT = "success theme default"
const val FAILED_THEME = "failed theme"
const val FAILED_THEME_DEFAULT = "failed theme default"

const val SUCCESS_LANGUAGE = "success language"
const val SUCCESS_LANGUAGE_DEFAULT = "success language default"
const val FAILED_LANGUAGE = "failed language"
const val FAILED_LANGUAGE_DEFAULT = "failed language default"

const val SUCCESS_THEME_CHECKBOX_STATE = "success theme checkbox state"
const val SUCCESS_THEME_CHECKBOX_STATE_DEFAULT = "success default theme checkbox state"
const val FAILED_THEME_CHECKBOX_STATE = "failed theme checkbox state"
const val FAILED_THEME_CHECKBOX_STATE__DEFAULT = "failed default theme checkbox state"

const val SUCCESS_LANGUAGE_CHECKBOX_STATE = "success default language checkbox state"
const val SUCCESS_LANGUAGE_CHECKBOX_STATE_DEFAULT = "success language checkbox state"
const val FAILED_LANGUAGE_CHECKBOX_STATE = "failed language checkbox state"
const val FAILED_LANGUAGE_CHECKBOX_STATE_DEFAULT = "failed default language checkbox state"

class AppRepositoryTest {

    lateinit var appRepository: AppRepository
    lateinit var sharedPreferencesDb: SharedPreferencesManager
    lateinit var context: Context

    @Before
    fun setUp() {
        context = mockk()
        sharedPreferencesDb = mockk()
        appRepository = AppRepository(sharedPreferencesDb, context)
    }

    //test cases
    //1-getCurrentTheme
    //if getCurrentTheme function getTheme successfully at first time app launching_themePassedAndGetWillBeEqualed
    @Test
    fun getCurrentTheme_ifThemeGetsSuccessfullyFromDataBaseAtFirstTimeAppLaunching_themePassedAndGetWillBeEqualed() {
        every { sharedPreferencesDb.getAppTheme(context) } returns SUCCESS_THEME_DEFAULT
        val result = appRepository.getCurrentTheme()
        assertEquals(result, SUCCESS_THEME_DEFAULT)
    }

    //if getCurrentTheme function getTheme successfully_themePassedAndGetWillBeEqualed
    @Test
    fun getCurrentTheme_ifThemeGetsSuccessfullyFromDataBase_themePassedAndGetWillBeEqualed() {
        every { sharedPreferencesDb.getAppTheme(context) } returns SUCCESS_THEME
        val result = appRepository.getCurrentTheme()
        assertEquals(result, SUCCESS_THEME)
    }

    //if getCurrentTheme function getTheme failed at first time app launching_themePassedAndGetWillNotBeEqualed
    @Test
    fun getCurrentTheme_ifThemeGetsFailedFromDataBaseAtFirstTimeAppLaunching_themePassedAndGetWillNotBeEqualed() {
        every { sharedPreferencesDb.getAppTheme(context) } returns FAILED_THEME_DEFAULT
        val result = appRepository.getCurrentTheme()
        assertNotEquals(result, SUCCESS_THEME_DEFAULT)
    }

    //if getCurrentTheme function getTheme failed_themePassedAndGetWillNotBeEqualed
    @Test
    fun getCurrentTheme_ifThemeGetsFailedFromDataBase_themePassedAndGetWillNotBeEqualed() {
        every { sharedPreferencesDb.getAppTheme(context) } returns FAILED_THEME
        val result = appRepository.getCurrentTheme()
        assertNotEquals(result, SUCCESS_THEME)
    }

    //2-getCurrentLanguage
    //if getCurrentLanguage function getLanguage successfully at first time app launching_languagePassedAndGetWillBeEqualed
    @Test
    fun getCurrentLanguage_ifLanguageGetsSuccessfullyFromDataBaseAtFirstTimeAppLaunching_languagePassedAndGetWillBeEqualed() {
        every { sharedPreferencesDb.getAppLanguage(context) } returns SUCCESS_LANGUAGE_DEFAULT
        val result = appRepository.getCurrentLanguage()
        assertEquals(result, SUCCESS_LANGUAGE_DEFAULT)
    }

    //if getCurrentLanguage function getLanguage successfully_languagePassedAndGetWillBeEqualed
    @Test
    fun getCurrentLanguage_ifLanguageGetsSuccessfullyFromDataBase_languagePassedAndGetWillBeEqualed() {
        every { sharedPreferencesDb.getAppLanguage(context) } returns SUCCESS_LANGUAGE
        val result = appRepository.getCurrentLanguage()
        assertEquals(result, SUCCESS_LANGUAGE)
    }

    //if getCurrentLanguage function getLanguage failed at first time app launching_languagePassedAndGetWillNotBeEqualed
    @Test
    fun getCurrentLanguage_ifLanguageGetsFailedFromDataBaseAtFirstTimeAppLaunching_languagePassedAndGetWillNotBeEqualed() {
        every { sharedPreferencesDb.getAppLanguage(context) } returns FAILED_LANGUAGE_DEFAULT
        val result = appRepository.getCurrentLanguage()
        assertNotEquals(result, SUCCESS_LANGUAGE_DEFAULT)
    }

    //if getCurrentLanguage function getLanguage failed_languagePassedAndGetWillNotBeEqualed
    @Test
    fun getCurrentLanguage_ifLanguageGetsFailedFromDataBase_languagePassedAndGetWillNotBeEqualed() {
        every { sharedPreferencesDb.getAppLanguage(context) } returns FAILED_LANGUAGE
        val result = appRepository.getCurrentLanguage()
        assertNotEquals(result, SUCCESS_LANGUAGE)
    }

    //3-getCheckedThemeCheckboxes
    //if getCheckedThemeCheckboxes function gets checkbox state successfully at first time app launching_true returned
    @Test
    fun getCheckedThemeCheckboxes_ifGetCheckboxStateSuccessfullyAtFirstTimeAppLaunching_trueReturned() {
        every {
            sharedPreferencesDb.getThemesCheckBoxState(
                context,
                SUCCESS_THEME_CHECKBOX_STATE_DEFAULT
            )
        } returns true

        val result = appRepository.getCheckedThemeCheckboxes(SUCCESS_THEME_CHECKBOX_STATE_DEFAULT)
        assertEquals(result, true)
    }

    //if getCheckedThemeCheckboxes function gets checkbox state successfully_true returned
    @Test
    fun getCheckedThemeCheckboxes_ifGetCheckboxStateSuccessfully_trueReturned() {
        every {
            sharedPreferencesDb.getThemesCheckBoxState(
                context,
                SUCCESS_THEME_CHECKBOX_STATE
            )
        } returns true

        val result = appRepository.getCheckedThemeCheckboxes(SUCCESS_THEME_CHECKBOX_STATE)
        assertEquals(result, true)
    }

    //if getCheckedThemeCheckboxes function failed gets checkbox state at first time app launching _false returned
    @Test
    fun getCheckedThemeCheckboxes_ifGetCheckboxStateFailedAtFirstTimeAppLaunching_FalseReturned() {
        every {
            sharedPreferencesDb.getThemesCheckBoxState(
                context,
                FAILED_THEME_CHECKBOX_STATE__DEFAULT
            )
        } returns false

        val result = appRepository.getCheckedThemeCheckboxes(FAILED_THEME_CHECKBOX_STATE__DEFAULT)
        assertEquals(result, false)
    }

    //if getCheckedThemeCheckboxes function failed gets checkbox state _false returned
    @Test
    fun getCheckedThemeCheckboxes_ifGetCheckboxStateFailed_FalseReturned() {
        every {
            sharedPreferencesDb.getThemesCheckBoxState(
                context,
                FAILED_THEME_CHECKBOX_STATE
            )
        } returns false

        val result = appRepository.getCheckedThemeCheckboxes(FAILED_THEME_CHECKBOX_STATE)
        assertEquals(result, false)
    }

    //4-getCheckedLanguageCheckboxes
    //if getCheckedLanguageCheckboxes function gets checkbox state successfully at first time app launching_true returned
    @Test
    fun getCheckedLanguageCheckboxes_ifGetCheckboxStateSuccessfullyAtFirstTimeAppLaunching_trueReturned() {
        every {
            sharedPreferencesDb.getLanguagesCheckBoxState(
                context,
                SUCCESS_LANGUAGE_CHECKBOX_STATE_DEFAULT
            )
        } returns true

        val result = appRepository.getCheckedLanguageCheckboxes(
            SUCCESS_LANGUAGE_CHECKBOX_STATE_DEFAULT
        )
        assertEquals(result, true)
    }

    //if getCheckedLanguageCheckboxes function gets checkbox state successfully_true returned
    @Test
    fun getCheckedLanguageCheckboxes_ifGetCheckboxStateSuccessfully_trueReturned() {
        every {
            sharedPreferencesDb.getLanguagesCheckBoxState(
                context,
                SUCCESS_LANGUAGE_CHECKBOX_STATE
            )
        } returns true

        val result = appRepository.getCheckedLanguageCheckboxes(SUCCESS_LANGUAGE_CHECKBOX_STATE)
        assertEquals(result, true)
    }

    //if getCheckedLanguageCheckboxes function failed gets checkbox state at first time app launching_false returned
    @Test
    fun getCheckedLanguageCheckboxes_ifGetCheckboxStateFailedAtFirstTimeAppLaunching_FalseReturned() {
        every {
            sharedPreferencesDb.getLanguagesCheckBoxState(
                context,
                FAILED_LANGUAGE_CHECKBOX_STATE_DEFAULT
            )
        } returns false

        val result = appRepository.getCheckedLanguageCheckboxes(
            FAILED_LANGUAGE_CHECKBOX_STATE_DEFAULT
        )
        assertEquals(result, false)
    }

    //if getCheckedLanguageCheckboxes function failed gets checkbox state _false returned
    @Test
    fun getCheckedLanguageCheckboxes_ifGetCheckboxStateFailed_FalseReturned() {
        every {
            sharedPreferencesDb.getLanguagesCheckBoxState(
                context,
                FAILED_LANGUAGE_CHECKBOX_STATE
            )
        } returns false

        val result = appRepository.getCheckedLanguageCheckboxes(FAILED_LANGUAGE_CHECKBOX_STATE)
        assertEquals(result, false)
    }
}