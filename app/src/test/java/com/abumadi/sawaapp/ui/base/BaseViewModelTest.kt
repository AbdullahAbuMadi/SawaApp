package com.abumadi.sawaapp.ui.base

import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.others.Constants
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

const val DEFAULT_THEME = Constants.DEFAULT_THEME
const val DEFAULT_LANGUAGE = Constants.DEFAULT_LANGUAGE
const val ENGLISH_LANGUAGE = Constants.ENGLISH_LANGUAGE_LOCALE
const val ARABIC_LANGUAGE = Constants.ARABIC_LANGUAGE_LOCALE
const val PINK_THEME = Constants.THEME_PINK
const val BLUE_THEME = Constants.THEME_BLUE

class BaseViewModelTest {
    lateinit var baseViewModel: BaseViewModel
    lateinit var baseUseCase: BaseUseCase

    @Before
    fun setUp() {
        baseUseCase = mockk()
        baseViewModel = BaseViewModel(baseUseCase)
    }

    //testcases
    //1-setAppTheme
    //if appThemeExecute default theme _current theme will be pink and is default will be true
    @Test
    fun setAppTheme_ifAppThemeExecuteDefaultTheme_currentThemeWillBePinkAndIsDefaultWillBeTrue() {
        every { baseUseCase.appThemeExecute() } returns DEFAULT_THEME
        baseViewModel.setAppTheme()
        val currentTheme = baseViewModel.getCurrentTheme()
        val defaultTheme = baseViewModel.getDefaultTheme()
        assertThat(currentTheme).isEqualTo(R.style.PinkStyle)
        assertThat(defaultTheme).isTrue()
    }

    //if appThemeExecute pink theme _current theme will be pink and is default will be false
    @Test
    fun setAppTheme_ifAppThemeExecutePinkTheme_currentThemeWillBePinkAndIsDefaultWillBeFalse() {
        every { baseUseCase.appThemeExecute() } returns PINK_THEME
        baseViewModel.setAppTheme()
        val currentTheme = baseViewModel.getCurrentTheme()
        val defaultTheme = baseViewModel.getDefaultTheme()
        assertThat(currentTheme).isEqualTo(R.style.PinkStyle)
        assertThat(defaultTheme).isFalse()
    }

    //if appThemeExecute blue theme _current theme will be blue and is default will be false
    @Test
    fun setAppTheme_ifAppThemeExecuteBlueTheme_currentThemeWillBeBlueAndIsDefaultWillBeFalse() {
        every { baseUseCase.appThemeExecute() } returns BLUE_THEME
        baseViewModel.setAppTheme()
        val currentTheme = baseViewModel.getCurrentTheme()
        val defaultTheme = baseViewModel.getDefaultTheme()
        assertThat(currentTheme).isEqualTo(R.style.BlueStyle)
        assertThat(defaultTheme).isFalse()
    }

    //2-setAppLanguage
    //if appLanguageExecute default language _current language will be eng and is default will be true
    @Test
    fun setAppLanguage_ifAppLanguageExecuteDefaultLanguage_currentLanguageWillBeEnglishAndIsDefaultWillBeTrue() {
        every { baseUseCase.appLanguageExecute() } returns DEFAULT_LANGUAGE
        baseViewModel.setAppLanguage()
        val currentLanguage = baseViewModel.getCurrentLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        assertThat(currentLanguage).isEqualTo(ENGLISH_LANGUAGE)
        assertThat(defaultLanguage).isTrue()
    }

    //if appLanguageExecute eng language _current language will be eng and is default will be false
    @Test
    fun setAppLanguage_ifAppLanguageExecuteEnglishLanguage_currentLanguageWillBeEnglishAndIsDefaultWillBeFalse() {
        every { baseUseCase.appLanguageExecute() } returns ENGLISH_LANGUAGE
        baseViewModel.setAppLanguage()
        val currentLanguage = baseViewModel.getCurrentLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        assertThat(currentLanguage).isEqualTo(ENGLISH_LANGUAGE)
        assertThat(defaultLanguage).isFalse()
    }
    //if appLanguageExecute ar language _current language will be ar and is default will be false
    @Test
    fun setAppLanguage_ifAppLanguageExecuteArabicLanguage_currentLanguageWillBeArabicAndIsDefaultWillBeFalse() {
        every { baseUseCase.appLanguageExecute() } returns ARABIC_LANGUAGE
        baseViewModel.setAppLanguage()
        val currentLanguage = baseViewModel.getCurrentLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        assertThat(currentLanguage).isEqualTo(ARABIC_LANGUAGE)
        assertThat(defaultLanguage).isFalse()
    }
}