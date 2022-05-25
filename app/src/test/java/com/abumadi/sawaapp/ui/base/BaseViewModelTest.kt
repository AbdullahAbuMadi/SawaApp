package com.abumadi.sawaapp.ui.base

import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.others.Constants
import org.junit.Assert.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

const val DEFAULT_THEME = Constants.DEFAULT_THEME
const val DEFAULT_LANGUAGE = Constants.DEFAULT_LANGUAGE
const val DEFAULT_UI = Constants.DEFAULT_UI
const val ENGLISH_LANGUAGE = Constants.ENGLISH_LANGUAGE_LOCALE
const val BUTTON_UI = Constants.BUTTON_UI
const val CHECK_IN_LAYOUT_UI = Constants.CHECK_IN_LAYOUT_UI
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
        assertEquals(currentTheme, R.style.PinkStyle)
        assertEquals(defaultTheme, true)
    }

    //if appThemeExecute pink theme _current theme will be pink and is default will be false
    @Test
    fun setAppTheme_ifAppThemeExecutePinkTheme_currentThemeWillBePinkAndIsDefaultWillBeFalse() {
        every { baseUseCase.appThemeExecute() } returns PINK_THEME
        baseViewModel.setAppTheme()
        val currentTheme = baseViewModel.getCurrentTheme()
        val defaultTheme = baseViewModel.getDefaultTheme()
        assertEquals(currentTheme, R.style.PinkStyle)
        assertEquals(defaultTheme, false)
    }

    //if appThemeExecute blue theme _current theme will be blue and is default will be false
    @Test
    fun setAppTheme_ifAppThemeExecuteBlueTheme_currentThemeWillBeBlueAndIsDefaultWillBeFalse() {
        every { baseUseCase.appThemeExecute() } returns BLUE_THEME
        baseViewModel.setAppTheme()
        val currentTheme = baseViewModel.getCurrentTheme()
        val defaultTheme = baseViewModel.getDefaultTheme()
        assertEquals(currentTheme, R.style.BlueStyle)
        assertEquals(defaultTheme, false)
    }

    //2-setAppLanguage
    //if appLanguageExecute default language _current language will be eng and is default will be true
    @Test
    fun setAppLanguage_ifAppLanguageExecuteDefaultLanguage_currentLanguageWillBeEnglishAndIsDefaultWillBeTrue() {
        every { baseUseCase.appLanguageExecute() } returns DEFAULT_LANGUAGE
        baseViewModel.setAppLanguage()
        val currentLanguage = baseViewModel.getCurrentLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        assertEquals(currentLanguage, ENGLISH_LANGUAGE)
        assertEquals(defaultLanguage, true)
    }

    //if appLanguageExecute eng language _current language will be eng and is default will be false
    @Test
    fun setAppLanguage_ifAppLanguageExecuteEnglishLanguage_currentLanguageWillBeEnglishAndIsDefaultWillBeFalse() {
        every { baseUseCase.appLanguageExecute() } returns ENGLISH_LANGUAGE
        baseViewModel.setAppLanguage()
        val currentLanguage = baseViewModel.getCurrentLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        assertEquals(currentLanguage, ENGLISH_LANGUAGE)
        assertEquals(defaultLanguage, false)
    }

    //if appLanguageExecute ar language _current language will be ar and is default will be false
    @Test
    fun setAppLanguage_ifAppLanguageExecuteArabicLanguage_currentLanguageWillBeArabicAndIsDefaultWillBeFalse() {
        every { baseUseCase.appLanguageExecute() } returns ARABIC_LANGUAGE
        baseViewModel.setAppLanguage()
        val currentLanguage = baseViewModel.getCurrentLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        assertEquals(currentLanguage, ARABIC_LANGUAGE)
        assertEquals(defaultLanguage, false)
    }

    //3-setAppCurrentUi
    //if appCurrentUiExecute default Ui _current Ui will be Button and is default will be true
    @Test
    fun setAppCurrentUi_ifAppCurrentUiExecutedDefaultUi_currentUiWillBeButtonUiAndIsDefaultWillBeTrue() {
        every { baseUseCase.appCurrentUiExecute() } returns DEFAULT_UI
        baseViewModel.setAppCurrentUi()
        val currentUi = baseViewModel.getCurrentUi()
        val defaultUi = baseViewModel.getDefaultUi()
        assertEquals(BUTTON_UI, currentUi)
        assertEquals(true, defaultUi)
    }

    //if appCurrentUiExecute Button Ui _current Ui will be Button and is default will be false
    @Test
    fun setAppCurrentUi_ifAppUiExecuteButtonUi_currentUiWillBeButtonAndIsDefaultWillBeFalse() {
        every { baseUseCase.appCurrentUiExecute() } returns BUTTON_UI
        baseViewModel.setAppCurrentUi()
        val currentUi = baseViewModel.getCurrentUi()
        val defaultUi = baseViewModel.getDefaultUi()
        assertEquals(BUTTON_UI, currentUi)
        assertEquals(false, defaultUi)
    }

    //if appCurrentUiExecute ar Ui _current Ui will be ar and is default will be false
    @Test
    fun setAppCurrentUi_ifAppUiExecuteCheckInUi_currentUiWillBeCheckInAndIsDefaultWillBeFalse() {
        every { baseUseCase.appCurrentUiExecute() } returns CHECK_IN_LAYOUT_UI
        baseViewModel.setAppCurrentUi()
        val currentUi = baseViewModel.getCurrentUi()
        val defaultUi = baseViewModel.getDefaultUi()
        assertEquals(CHECK_IN_LAYOUT_UI, currentUi)
        assertEquals(false, defaultUi)
    }
}