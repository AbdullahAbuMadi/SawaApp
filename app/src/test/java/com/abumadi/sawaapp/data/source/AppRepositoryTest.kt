package com.abumadi.sawaapp.data.source

import android.content.Context
import com.abumadi.sawaapp.db.SharedPreferencesDb
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk

import org.junit.Before
import org.junit.Test

const val SUCCESS_THEME = "success theme"
const val FAILED_THEME = "failed theme"

const val SUCCESS_THEME_CHECKBOX_STATE = "success theme checkbox state"
const val FAILED_THEME_CHECKBOX_STATE = "failed theme checkbox state"

const val SUCCESS_LANGUAGE_CHECKBOX_STATE = "success language checkbox state"
const val FAILED_LANGUAGE_CHECKBOX_STATE = "failed language checkbox state"

class AppRepositoryTest {

    lateinit var appRepository: AppRepository
    lateinit var sharedPreferencesDb: SharedPreferencesDb
    lateinit var context: Context

    @Before
    fun setUp() {
        context = mockk()
        sharedPreferencesDb = mockk()
        appRepository = AppRepository(sharedPreferencesDb, context)
    }

    //test cases
    //1-getCurrentTheme
    //if getCurrentTheme function getTheme successfully_themePassedAndGetWillBeEqualed
    @Test
    fun getCurrentTheme_ifThemeGetsSuccessfullyFromDataBase_themePassedAndGetWillBeEqualed() {
        every { sharedPreferencesDb.getAppTheme(context) } returns SUCCESS_THEME
        val result = appRepository.getCurrentTheme()
        assertThat(result).isEqualTo(SUCCESS_THEME)
    }

    //if getCurrentTheme function getTheme failed_themePassedAndGetWillNotBeEqualed
    @Test
    fun getCurrentTheme_ifThemeGetsFailedFromDataBase_themePassedAndGetWillNotBeEqualed() {
        every { sharedPreferencesDb.getAppTheme(context) } returns FAILED_THEME
        val result = appRepository.getCurrentTheme()
        assertThat(result).isNotEqualTo(SUCCESS_THEME)
    }

    //2-getCheckedThemeCheckboxes
    //if getCheckedThemeCheckboxes function gets checkbox state successfully_true returned
    @Test
    fun getCheckedThemeCheckboxes_ifGetCheckboxStateSuccessfully_trueReturned() {
        every {
            sharedPreferencesDb.getThemesChickBoxState(
                context,
                SUCCESS_THEME_CHECKBOX_STATE
            )
        } returns true

        val result = appRepository.getCheckedThemeCheckboxes(SUCCESS_THEME_CHECKBOX_STATE)
        assertThat(result).isTrue()
    }
    //if getCheckedThemeCheckboxes function failed gets checkbox state _false returned
    @Test
    fun getCheckedThemeCheckboxes_ifGetCheckboxStateFailed_FalseReturned() {
        every {
            sharedPreferencesDb.getThemesChickBoxState(
                context,
                FAILED_THEME_CHECKBOX_STATE
            )
        } returns false

        val result = appRepository.getCheckedThemeCheckboxes(FAILED_THEME_CHECKBOX_STATE)
        assertThat(result).isFalse()
    }

    //3-getCheckedLanguageCheckboxes
    //if getCheckedLanguageCheckboxes function gets checkbox state successfully_true returned
    @Test
    fun getCheckedLanguageCheckboxes_ifGetCheckboxStateSuccessfully_trueReturned() {
        every {
            sharedPreferencesDb.getLanguagesChickBoxState(
                context,
                SUCCESS_LANGUAGE_CHECKBOX_STATE
            )
        } returns true

        val result = appRepository.getCheckedLanguageCheckboxes(SUCCESS_LANGUAGE_CHECKBOX_STATE)
        assertThat(result).isTrue()
    }
    //if getCheckedLanguageCheckboxes function failed gets checkbox state _false returned
    @Test
    fun getCheckedLanguageCheckboxes_ifGetCheckboxStateFailed_FalseReturned() {
        every {
            sharedPreferencesDb.getLanguagesChickBoxState(
                context,
                FAILED_LANGUAGE_CHECKBOX_STATE
            )
        } returns false

        val result = appRepository.getCheckedLanguageCheckboxes(FAILED_LANGUAGE_CHECKBOX_STATE)
        assertThat(result).isFalse()
    }

}