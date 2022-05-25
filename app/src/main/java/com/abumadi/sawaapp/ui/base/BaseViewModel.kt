package com.abumadi.sawaapp.ui.base

import androidx.lifecycle.ViewModel
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.others.Constants
import javax.inject.Inject

class BaseViewModel @Inject constructor(private val baseUseCase: BaseUseCase) : ViewModel() {

    private var currentTheme: Int? = null
    private var currentLanguage: String? = null
    private var currentUi: String? = null
    private var isDefaultTheme: Boolean? = null
    private var isDefaultLanguage: Boolean? = null
    private var isDefaultUi: Boolean? = null

    fun setAppTheme() {
        if (baseUseCase.appThemeExecute() == Constants.DEFAULT_THEME) {
            currentTheme = R.style.PinkStyle
            isDefaultTheme = true
        } else {
            if (baseUseCase.appThemeExecute() == Constants.THEME_PINK) {
                currentTheme = R.style.PinkStyle
            } else if (baseUseCase.appThemeExecute() == Constants.THEME_BLUE) {
                currentTheme = R.style.BlueStyle
            }
            isDefaultTheme = false
        }
    }

    fun setAppLanguage() {
        if (baseUseCase.appLanguageExecute() == Constants.DEFAULT_LANGUAGE) {
            currentLanguage = Constants.ENGLISH_LANGUAGE_LOCALE
            isDefaultLanguage = true
        } else {
            if (baseUseCase.appLanguageExecute() == Constants.ENGLISH_LANGUAGE_LOCALE) {
                currentLanguage = Constants.ENGLISH_LANGUAGE_LOCALE
                isDefaultLanguage = false
            } else if (baseUseCase.appLanguageExecute() == Constants.ARABIC_LANGUAGE_LOCALE) {
                currentLanguage = Constants.ARABIC_LANGUAGE_LOCALE
                isDefaultLanguage = false
            }
        }
    }

    fun setAppCurrentUi() {
        if (baseUseCase.appCurrentUiExecute() == Constants.DEFAULT_UI) {
            currentUi = Constants.BUTTON_UI
            isDefaultUi = true
        } else {
            if (baseUseCase.appCurrentUiExecute() == Constants.BUTTON_UI) {
                currentUi = Constants.BUTTON_UI
                isDefaultUi = false
            } else if (baseUseCase.appCurrentUiExecute() == Constants.CHECK_IN_LAYOUT_UI) {
                currentUi = Constants.CHECK_IN_LAYOUT_UI
                isDefaultUi = false
            }
        }
    }

    fun getCurrentTheme(): Int? {
        return this.currentTheme
    }

    fun getDefaultTheme(): Boolean? {
        return this.isDefaultTheme
    }

    fun getCurrentLanguage(): String? {
        return this.currentLanguage
    }

    fun getDefaultLanguage(): Boolean? {
        return this.isDefaultLanguage
    }

    fun getCurrentUi(): String? {
        return this.currentUi
    }

    fun getDefaultUi(): Boolean? {
        return this.isDefaultUi
    }
}
