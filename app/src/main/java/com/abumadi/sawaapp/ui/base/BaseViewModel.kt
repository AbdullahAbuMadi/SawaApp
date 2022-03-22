package com.abumadi.sawaapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.others.util.SingleLiveEvent
import javax.inject.Inject

class BaseViewModel @Inject constructor(private val baseUseCase: BaseUseCase) : ViewModel() {

    private var currentTheme: Int? = null
    private var currentLanguage: String? = null
    private var isDefaultTheme: Boolean? = null
    private var isDefaultLanguage: Boolean? = null

    fun setAppTheme() {
        if (baseUseCase.appThemeExecute() == Constants.DEFAULT_THEME) {
            currentTheme = R.style.PinkStyle
            isDefaultTheme = true
        } else {
            if (baseUseCase.appThemeExecute() == Constants.THEME_PINK) {
                currentTheme = R.style.PinkStyle
                isDefaultTheme = false
            } else if (baseUseCase.appThemeExecute() == Constants.THEME_BLUE) {
                currentTheme = R.style.BlueStyle
                isDefaultTheme = false
            }
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
}

//class RefreshableLiveData<T>(
//    private val source: () -> LiveData<T>
//) : MediatorLiveData<T>() {
//
//    private var liveData = source()
//
//    init {
//        this.addSource(liveData, ::observer)
//    }
//
//    private fun observer(data: T) {
//        value = data
//    }
//
//    fun refresh() {
//        this.removeSource(liveData)
//        liveData = source()
//        this.addSource(liveData, ::observer)
//    }
//}

//
//    private val _currentTheme = SingleLiveEvent<Int>()

//    val currentTheme: LiveData<Int>
//        get() = _currentTheme

//livedata
//    fun setAppTheme() {
//        if (baseUseCase.appThemeExecute() == Constants.DEFAULT_THEME || baseUseCase.appThemeExecute() == Constants.THEME_PINK) {
//            _currentTheme.value = R.style.PinkStyle
//        } else if (baseUseCase.appThemeExecute() == Constants.THEME_BLUE) {
//            _currentTheme.value = R.style.BlueStyle
//        }
//    }
