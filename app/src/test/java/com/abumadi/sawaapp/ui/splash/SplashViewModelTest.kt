package com.abumadi.sawaapp.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.SplashUseCase
import com.abumadi.sawaapp.others.Constants
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

const val DEFAULT_THEME = Constants.THEME_PINK
const val FAILED_THEME = "failed"
const val PINK_THEME = Constants.THEME_PINK
const val BLUE_THEME = Constants.THEME_BLUE

class SplashViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule =
        InstantTaskExecutorRule()

    lateinit var splashViewModel: SplashViewModel
    lateinit var splashUseCase: SplashUseCase

    @Before
    fun setUp() {
        splashUseCase = mockk()
        splashViewModel = SplashViewModel(splashUseCase)
    }

    //testcases
    //setCurrentSplashLogo
    //if appThemeExecute successfully and default theme executed_currentSplashLogo will be pink logo
    @Test
    fun setCurrentSplashLogo_ifAppThemeExecuteSuccessfullyAndDefaultThemeExecuted_currentSplashLogoLiveDataValueWillBePinkLogo() {
        every { splashUseCase.appThemeExecute() } returns DEFAULT_THEME
        splashViewModel.setCurrentSplashLogo()
        val result = splashViewModel.currentSplashLogo.value
        assertThat(result).isEqualTo(R.drawable.ic_sawa_logo_pink)
    }
    //if appThemeExecute successfully and pink theme executed_currentSplashLogo will be pink logo
    @Test
    fun setCurrentSplashLogo_ifAppThemeExecuteSuccessfullyAndPinkThemeExecuted_currentSplashLogoLiveDataValueWillBePinkLogo() {
        every { splashUseCase.appThemeExecute() } returns PINK_THEME
        splashViewModel.setCurrentSplashLogo()
        val result = splashViewModel.currentSplashLogo.value
        assertThat(result).isEqualTo(R.drawable.ic_sawa_logo_pink)
    }
    //if appThemeExecute successfully and blue theme executed_currentSplashLogo will be blue logo
    @Test
    fun setCurrentSplashLogo_ifAppThemeExecuteSuccessfullyAndBlueThemeExecuted_currentSplashLogoLiveDataValueWillBeBlueLogo() {
        every { splashUseCase.appThemeExecute() } returns BLUE_THEME
        splashViewModel.setCurrentSplashLogo()
        val result = splashViewModel.currentSplashLogo.value
        assertThat(result).isEqualTo(R.drawable.ic_sawa_logo_blue)
    }
    //if appThemeExecute failed _currentSplashLogo will be null
    @Test
    fun setCurrentSplashLogo_ifAppThemeExecuteFailedExecuted_currentSplashLogoLiveDataValueWillBeNull() {
        every { splashUseCase.appThemeExecute() } returns FAILED_THEME
        splashViewModel.setCurrentSplashLogo()
        val result = splashViewModel.currentSplashLogo.value
        assertThat(result).isEqualTo(null)
    }
}