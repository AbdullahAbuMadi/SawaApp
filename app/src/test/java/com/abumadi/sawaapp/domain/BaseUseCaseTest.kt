package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.data.source.AppRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFails

const val SUCCESS_THEME_PASSED = "success theme"
const val SUCCESS_LANGUAGE_PASSED = "success language"
const val SUCCESS_UI_PASSED = "success ui"
const val DEFAULT_THEME_PASSED = "default theme"
const val DEFAULT_LANGUAGE_PASSED = "default language"
const val DEFAULT_Ui_PASSED = "default ui"
const val FAILED_THEME_PASSED = "failed theme"
const val FAILED_LANGUAGE_PASSED = "failed language"

private val FAILED_UI_PASSED = Exception("failed ui passed")

class BaseUseCaseTest {
    lateinit var baseUseCase: BaseUseCase
    lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        appRepository = mockk()
        baseUseCase = BaseUseCase(appRepository)
    }

    //testcases
    //1-appThemeExecute
    //if get current theme successfully_theme got will be the same theme passed
    @Test
    fun appThemeExecute_ifGetCurrentThemeSuccessfully_themeGotWillBeTheSameThatPassed() {
        every { appRepository.getCurrentTheme() } returns SUCCESS_THEME_PASSED
        val result = baseUseCase.appThemeExecute()
        assertThat(result).isEqualTo(SUCCESS_THEME_PASSED)
    }

    //if failed get current theme_theme got will not be the same theme passed
    @Test
    fun appThemeExecute_ifGetCurrentThemeFailed_themeGotWillNotBeTheSameThatPassed() {
        every { appRepository.getCurrentTheme() } returns FAILED_THEME_PASSED
        val result = baseUseCase.appThemeExecute()
        assertThat(result).isNotEqualTo(SUCCESS_THEME_PASSED)
    }

    //if get current default theme at first time app launching_theme got will be the default theme
    @Test
    fun appThemeExecute_ifGetDefaultThemeSuccessfullyAtFirstTimeAppLaunching_themeGotWillBeTheDefault() {
        every { appRepository.getCurrentTheme() } returns DEFAULT_THEME_PASSED
        val result = baseUseCase.appThemeExecute()
        assertThat(result).isEqualTo(DEFAULT_THEME_PASSED)
    }

    //2-appLanguageExecute
    //if get current language successfully_language got will be the same language passed
    @Test
    fun appLanguageExecute_ifGetCurrentLanguageSuccessfully_languageGotWillBeTheSameThatPassed() {
        every { appRepository.getCurrentLanguage() } returns SUCCESS_LANGUAGE_PASSED
        val result = baseUseCase.appLanguageExecute()
        assertThat(result).isEqualTo(SUCCESS_LANGUAGE_PASSED)
    }

    //if failed get current language_language got will not be the same language passed
    @Test
    fun appLanguageExecute_ifGetCurrentLanguageFailed_languageGotWillNotBeTheSameThatPassed() {
        every { appRepository.getCurrentLanguage() } returns FAILED_LANGUAGE_PASSED
        val result = baseUseCase.appLanguageExecute()
        assertThat(result).isNotEqualTo(SUCCESS_LANGUAGE_PASSED)
    }

    //if get current default language at first time app launching_language got will be the default language
    @Test
    fun appLanguageExecute_ifGetDefaultLanguage_languageGotWillBeTheDefaultLanguage() {
        every { appRepository.getCurrentLanguage() } returns DEFAULT_LANGUAGE_PASSED
        val result = baseUseCase.appLanguageExecute()
        assertThat(result).isEqualTo(DEFAULT_LANGUAGE_PASSED)
    }

    //3-appCurrentUiExecute
    //if get current Ui successfully_Ui got will be the same Ui passed
    @Test
    fun appCurrentUiExecute_ifGetCurrentLanguageSuccessfully_UiGotWillBeTheSameThatPassed() {
        every { appRepository.getCurrentUi() } returns SUCCESS_UI_PASSED
        val result = baseUseCase.appCurrentUiExecute()
        assertEquals(SUCCESS_UI_PASSED, result)
    }

    //if failed get current Ui_Ui got will not be the same Ui passed
    @Test
    fun appCurrentUiExecute_ifGetCurrentLanguageFailed_UiGotWillNotBeTheSameThatPassed() {
        every { appRepository.getCurrentUi() } throws FAILED_UI_PASSED
        val result = assertFails { baseUseCase.appCurrentUiExecute() }
        assertEquals(FAILED_UI_PASSED, result)
    }

    //if get current default Ui at first time app launching_Ui got will be the default Ui
    @Test
    fun appCurrentUiExecute_ifGetDefaultLanguage_UiGotWillBeTheDefaultLanguage() {
        every { appRepository.getCurrentUi() } returns DEFAULT_Ui_PASSED
        val result = baseUseCase.appCurrentUiExecute()
        assertEquals(DEFAULT_Ui_PASSED, result)
    }
}