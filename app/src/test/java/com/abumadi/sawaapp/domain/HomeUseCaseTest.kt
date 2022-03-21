package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.data.source.AppRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

const val THEME_DEFAULT_CHECKED_CHECKBOX_SUCCESS_KEY = "default theme checked checkbox success key"
const val THEME_DEFAULT_CHECKED_CHECKBOX_FAILED_KEY = "default theme checked checkbox failed key"
const val THEME_DEFAULT_CHECKBOX_STATE_SUCCESS = true
const val THEME_DEFAULT_CHECKBOX_STATE_FAILED = false

const val THEME_CHECKED_CHECKBOX_SUCCESS_KEY = "theme checked checkbox success key"
const val THEME_CHECKED_CHECKBOX_FAILED_KEY = "theme checked checkbox failed key"
const val THEME_CHECKBOX_STATE_SUCCESS = true
const val THEME_CHECKBOX_STATE_FAILED = false

const val LANGUAGE_CHECKED_CHECKBOX_SUCCESS_KEY = "language checked checkbox success key"
const val LANGUAGE_CHECKED_CHECKBOX_FAILED_KEY = "language checked checkbox failed key"
const val LANGUAGE_CHECKBOX_STATE_SUCCESS = true
const val LANGUAGE_CHECKBOX_STATE_FAILED = false

const val LANGUAGE_DEFAULT_CHECKED_CHECKBOX_SUCCESS_KEY =
    "default language checked checkbox success key"
const val LANGUAGE_DEFAULT_CHECKED_CHECKBOX_FAILED_KEY =
    "default language checked checkbox failed key"
const val LANGUAGE_DEFAULT_CHECKBOX_STATE_SUCCESS = true
const val LANGUAGE_DEFAULT_CHECKBOX_STATE_FAILED = false

class HomeUseCaseTest {
    lateinit var homeUseCase: HomeUseCase
    lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        appRepository = mockk()
        homeUseCase = HomeUseCase(appRepository)
    }

    //testcases
    //1-themeCheckboxStateExecuted
    //themeCheckboxStateExecuted_if successfully executed at first time app launching_theme checkbox state will be true
    @Test
    fun themeCheckboxStateExecuted_ifSuccessfullyExecutedAtFirstTimeAppLaunching_themeCheckboxStateWillBeTrue() {
        every { appRepository.getCheckedThemeCheckboxes(THEME_DEFAULT_CHECKED_CHECKBOX_SUCCESS_KEY) } returns THEME_DEFAULT_CHECKBOX_STATE_SUCCESS
        val result =
            homeUseCase.themeCheckboxStateExecuted(THEME_DEFAULT_CHECKED_CHECKBOX_SUCCESS_KEY)
        assertThat(result).isTrue()
    }

    //themeCheckboxStateExecuted_if successfully executed_theme checkbox state will be true
    @Test
    fun themeCheckboxStateExecuted_ifSuccessfullyExecuted_themeCheckboxStateWillBeTrue() {
        every { appRepository.getCheckedThemeCheckboxes(THEME_CHECKED_CHECKBOX_SUCCESS_KEY) } returns THEME_CHECKBOX_STATE_SUCCESS
        val result = homeUseCase.themeCheckboxStateExecuted(THEME_CHECKED_CHECKBOX_SUCCESS_KEY)
        assertThat(result).isTrue()
    }

    //themeCheckboxStateExecuted_if failed executed at first time executed_theme checkbox state will be false
    @Test
    fun themeCheckboxStateExecuted_ifFailedExecutedAtFirstTimeAppLaunching_themeCheckboxStateWillBeFalse() {
        every { appRepository.getCheckedThemeCheckboxes(THEME_DEFAULT_CHECKED_CHECKBOX_FAILED_KEY) } returns THEME_DEFAULT_CHECKBOX_STATE_FAILED
        val result = homeUseCase.themeCheckboxStateExecuted(THEME_DEFAULT_CHECKED_CHECKBOX_FAILED_KEY)
        assertThat(result).isFalse()
    }

    //themeCheckboxStateExecuted_if failed executed_theme checkbox state will  be false
    @Test
    fun themeCheckboxStateExecuted_ifFailedExecuted_themeCheckboxStateWillBeFalse() {
        every { appRepository.getCheckedThemeCheckboxes(THEME_CHECKED_CHECKBOX_FAILED_KEY) } returns THEME_CHECKBOX_STATE_FAILED
        val result = homeUseCase.themeCheckboxStateExecuted(THEME_CHECKED_CHECKBOX_FAILED_KEY)
        assertThat(result).isFalse()
    }

    //2-languageCheckboxStateExecuted
    //languageCheckboxStateExecuted_if successfully executed at first time app launching_language checkbox state will be true
    @Test
    fun languageCheckboxStateExecuted_ifSuccessfullyExecutedAtFirstTimeAppLaunching_languageCheckboxStateWillBeTrue() {
        every { appRepository.getCheckedLanguageCheckboxes(LANGUAGE_DEFAULT_CHECKED_CHECKBOX_SUCCESS_KEY) } returns LANGUAGE_DEFAULT_CHECKBOX_STATE_SUCCESS
        val result =
            homeUseCase.languageCheckboxStateExecuted(LANGUAGE_DEFAULT_CHECKED_CHECKBOX_SUCCESS_KEY)
        assertThat(result).isTrue()
    }
    //languageCheckboxStateExecuted_if successfully executed_language checkbox state  will be true
    @Test
    fun languageCheckboxStateExecuted_ifSuccessfullyExecuted_languageCheckboxStateWillBeTrue() {
        every { appRepository.getCheckedLanguageCheckboxes(LANGUAGE_CHECKED_CHECKBOX_SUCCESS_KEY) } returns LANGUAGE_CHECKBOX_STATE_SUCCESS
        val result =
            homeUseCase.languageCheckboxStateExecuted(LANGUAGE_CHECKED_CHECKBOX_SUCCESS_KEY)
        assertThat(result).isTrue()
    }

    //languageCheckboxStateExecuted_if failed executed at first time app launching_language checkbox state will be false
    @Test
    fun languageCheckboxStateExecuted_ifFailedExecutedAtFirstTimeAppLaunching_languageCheckboxStateWillBeFalse() {
        every { appRepository.getCheckedLanguageCheckboxes(LANGUAGE_DEFAULT_CHECKED_CHECKBOX_FAILED_KEY) } returns LANGUAGE_DEFAULT_CHECKBOX_STATE_FAILED
        val result = homeUseCase.languageCheckboxStateExecuted(LANGUAGE_DEFAULT_CHECKED_CHECKBOX_FAILED_KEY)
        assertThat(result).isFalse()
    }

    //languageCheckboxStateExecuted_if failed executed_language checkbox state will be false
    @Test
    fun languageCheckboxStateExecuted_ifFailedExecuted_languageCheckboxStateWillBeFalse() {
        every { appRepository.getCheckedLanguageCheckboxes(LANGUAGE_CHECKED_CHECKBOX_FAILED_KEY) } returns LANGUAGE_CHECKBOX_STATE_FAILED
        val result = homeUseCase.languageCheckboxStateExecuted(LANGUAGE_CHECKED_CHECKBOX_FAILED_KEY)
        assertThat(result).isFalse()
    }
}