package com.abumadi.sawaapp.domain

import com.abumadi.sawaapp.data.source.AppRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

const val DEFAULT_APP_THEME = "default app theme"
const val SUCCESS_APP_THEME = "success app theme"
const val FAILED_APP_THEME = "failed app theme"

class SplashUseCaseTest {
    lateinit var splashUseCase: SplashUseCase
    lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        appRepository = mockk()
        splashUseCase = SplashUseCase(appRepository)
    }

    //testcases
    //appThemeExecute
    //if app theme success executed at first app launching_default theme passed and result will be the same
    @Test
    fun appThemeExecute_ifAppThemeSuccessExecutedAtFirstAppLaunching_defaultThemePassedAndResultWillBeTheSame() {
        every { appRepository.getCurrentTheme() } returns DEFAULT_APP_THEME
        val result = splashUseCase.appThemeExecute()
        assertThat(result).isEqualTo(DEFAULT_APP_THEME)
    }

    //if app theme success executed_theme passes and result will be the same
    @Test
    fun appThemeExecute_ifAppThemeSuccessExecuted_themePassedAndResultWillBeTheSame() {
        every { appRepository.getCurrentTheme() } returns SUCCESS_APP_THEME
        val result = splashUseCase.appThemeExecute()
        assertThat(result).isEqualTo(SUCCESS_APP_THEME)
    }

    //if app theme failed executed_theme passes and result will not be the same
    @Test
    fun appThemeExecute_ifAppThemeFailedExecuted_themePassedAndResultWillNotBeTheSame() {
        every { appRepository.getCurrentTheme() } returns FAILED_APP_THEME
        val result = splashUseCase.appThemeExecute()
        assertThat(result).isNotEqualTo(SUCCESS_APP_THEME)
    }
}