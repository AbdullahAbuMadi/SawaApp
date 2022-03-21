package com.abumadi.sawaapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abumadi.sawaapp.domain.HomeUseCase
import com.abumadi.sawaapp.others.Constants
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

const val FAKE_DEFAULT_THEME_CHECKBOX_CHECKED_KEY = Constants.PINK_CHECKBOX_CHECKED
const val FAKE_BLUE_CHECKBOX_CHECKED_KEY = Constants.BLUE_CHECKBOX_CHECKED
const val FAKE_PINK_CHECKBOX_CHECKED_KEY = Constants.PINK_CHECKBOX_CHECKED

const val FAKE_DEFAULT_LANGUAGE_CHECKBOX_CHECKED_KEY = Constants.ENG_CHECKBOX_CHECKED
const val FAKE_ENG_CHECKBOX_CHECKED = Constants.ENG_CHECKBOX_CHECKED
const val FAKE_ARAB_CHECKBOX_CHECKED = Constants.ARAB_CHECKBOX_CHECKED

class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule =
        InstantTaskExecutorRule()

    lateinit var homeViewModel: HomeViewModel
    lateinit var homeUseCase: HomeUseCase

    @Before
    fun setUp() {
        homeUseCase = mockk()
        homeViewModel = HomeViewModel(homeUseCase)
    }

    //testcases
    //1-themeCheckboxesBehavior
    //if themeCheckboxStateExecuted success and default checkbox checked_isPinkCheckboxChecked live data value will be true
    @Test
    fun themeCheckboxesBehavior_ifThemeCheckboxStateExecutedSuccessfullyAndDefaultCheckboxChecked_isPinkCheckboxCheckedLiveDataValueWillBeTrue() {
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_DEFAULT_THEME_CHECKBOX_CHECKED_KEY) } returns true
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_BLUE_CHECKBOX_CHECKED_KEY) } returns false
        homeViewModel.themeCheckboxesBehavior()
        val result = homeViewModel.isPinkCheckboxChecked.value
        assertThat(result).isTrue()
    }

    //if themeCheckboxStateExecuted success and blue checkbox checked_isBlueCheckboxChecked live data value will be true
    @Test
    fun themeCheckboxesBehavior_ifThemeCheckboxStateExecutedSuccessfullyAndBlueCheckboxChecked_isBlueCheckboxCheckedLiveDataValueWillBeTrue() {
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_BLUE_CHECKBOX_CHECKED_KEY) } returns true
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_PINK_CHECKBOX_CHECKED_KEY) } returns false
        homeViewModel.themeCheckboxesBehavior()
        val result = homeViewModel.isBlueCheckboxChecked.value
        assertThat(result).isTrue()
    }

    //if themeCheckboxStateExecuted success and pink checkbox checked_isPinkCheckboxChecked live data value will be true
    @Test
    fun themeCheckboxesBehavior_ifThemeCheckboxStateExecutedSuccessfullyAndPinkCheckboxChecked_isPinkCheckboxCheckedLiveDataValueWillBeTrue() {
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_BLUE_CHECKBOX_CHECKED_KEY) } returns false
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_PINK_CHECKBOX_CHECKED_KEY) } returns true
        homeViewModel.themeCheckboxesBehavior()
        val result = homeViewModel.isPinkCheckboxChecked.value
        assertThat(result).isTrue()
    }

    //if themeCheckboxStateExecuted failed get checked checkbox_livedata values will be null
    @Test
    fun themeCheckboxesBehavior_ifThemeCheckboxStateExecutedFailed_AllLiveDataWillBeNull() {
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_BLUE_CHECKBOX_CHECKED_KEY) } returns false
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_PINK_CHECKBOX_CHECKED_KEY) } returns false
        every { homeUseCase.themeCheckboxStateExecuted(FAKE_DEFAULT_THEME_CHECKBOX_CHECKED_KEY) } returns false
        homeViewModel.themeCheckboxesBehavior()
        val resultPink = homeViewModel.isPinkCheckboxChecked.value
        val resultBlue = homeViewModel.isBlueCheckboxChecked.value
        assertThat(resultBlue).isEqualTo(null)
        assertThat(resultPink).isEqualTo(null)
    }

    //2-languageCheckboxesBehavior
    //if languageCheckboxStateExecuted success and default checkbox checked_isEnglishCheckboxChecked live data value will be true
    @Test
    fun languageCheckboxesBehavior_ifLanguageCheckboxStateExecutedSuccessfullyAndDefaultCheckboxChecked_isEnglishCheckboxCheckedLiveDataValueWillBeTrue() {
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_DEFAULT_LANGUAGE_CHECKBOX_CHECKED_KEY) } returns true
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ARAB_CHECKBOX_CHECKED) } returns false
        homeViewModel.languageCheckboxesBehavior()
        val result = homeViewModel.isEnglishCheckboxChecked.value
        assertThat(result).isTrue()
    }
    //if languageCheckboxStateExecuted success and blue checkbox checked_isEnglishCheckboxChecked live data value will be true
    @Test
    fun languageCheckboxesBehavior_ifLanguageCheckboxStateExecutedSuccessfullyAndEnglishCheckboxChecked_isEnglishCheckboxCheckedLiveDataValueWillBeTrue() {
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ENG_CHECKBOX_CHECKED) } returns true
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ARAB_CHECKBOX_CHECKED) } returns false
        homeViewModel.languageCheckboxesBehavior()
        val result = homeViewModel.isEnglishCheckboxChecked.value
        assertThat(result).isTrue()
    }
    //if languageCheckboxStateExecuted success and pink checkbox checked _isArabicCheckboxChecked live data value will be true
    @Test
    fun languageCheckboxesBehavior_ifLanguageCheckboxStateExecutedSuccessfullyAndArabicCheckboxChecked_isArabicCheckboxCheckedLiveDataValueWillBeTrue() {
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ENG_CHECKBOX_CHECKED) } returns false
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ARAB_CHECKBOX_CHECKED) } returns true
        homeViewModel.languageCheckboxesBehavior()
        val result = homeViewModel.isArabicCheckboxChecked.value
        assertThat(result).isTrue()
    }
    //if languageCheckboxStateExecuted failed get checked checkbox_livedata values will be false
    @Test
    fun themeCheckboxesBehavior_ifLanguageCheckboxStateExecutedFailed_AllLiveDataWillBeNull() {
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_DEFAULT_LANGUAGE_CHECKBOX_CHECKED_KEY) } returns false
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ENG_CHECKBOX_CHECKED) } returns false
        every { homeUseCase.languageCheckboxStateExecuted(FAKE_ARAB_CHECKBOX_CHECKED) } returns false
        homeViewModel.languageCheckboxesBehavior()
        val resultEnglish = homeViewModel.isEnglishCheckboxChecked.value
        val resultArabic = homeViewModel.isArabicCheckboxChecked.value
        assertThat(resultArabic).isEqualTo(null)
        assertThat(resultEnglish).isEqualTo(null)
    }
}