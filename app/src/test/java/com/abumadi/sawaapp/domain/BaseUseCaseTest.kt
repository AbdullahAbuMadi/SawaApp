package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.data.source.AppRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

const val SUCCESS_THEME_PASSED = "success theme"
const val SUCCESS_LANGUAGE_PASSED = "success language"
const val DEFAULT_THEME_PASSED = "default theme"
const val DEFAULT_LANGUAGE_PASSED = "default language"
const val FAILED_THEME_PASSED = "failed theme"
const val FAILED_LANGUAGE_PASSED = "failed language"

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
}