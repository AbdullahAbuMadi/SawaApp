package com.abumadi.sawaapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.others.util.SingleLiveEvent
import javax.inject.Inject

class BaseViewModel @Inject constructor(
    private val baseUseCase: BaseUseCase,
) : ViewModel() {

    private val _applicationLanguage = RefreshableLiveData {
        baseUseCase.getAppLanguage()
    }
    private val _defaultLanguageCheckedCheckboxObserve = SingleLiveEvent<String>()

    private val _applicationTheme = RefreshableLiveData {
        baseUseCase.getAppTheme()
    }
    private val _defaultThemeCheckedCheckboxObserve = SingleLiveEvent<String>()


    val applicationLanguage: RefreshableLiveData<String?>
        get() = _applicationLanguage

    val applicationTheme: RefreshableLiveData<Int?>
        get() = _applicationTheme

    val defaultLanguageCheckedCheckbox: LiveData<String?>
        get() = _defaultLanguageCheckedCheckboxObserve

    val defaultThemeCheckedCheckbox: LiveData<String>
        get() = _defaultThemeCheckedCheckboxObserve

    fun setAppLanguage() {
        if (baseUseCase.getAppLanguage().value == Constants.DEFAULT_LANGUAGE) {
            _applicationLanguage.value = Constants.ENGLISH_LANGUAGE_LOCALE
            _defaultLanguageCheckedCheckboxObserve.value = Constants.ENG_CHECKBOX_CHECKED
        } else {
            if (baseUseCase.getAppLanguage().value == Constants.ENGLISH_LANGUAGE_LOCALE) {
                _applicationLanguage.value = Constants.ENGLISH_LANGUAGE_LOCALE

            } else if (baseUseCase.getAppLanguage().value == Constants.ARABIC_LANGUAGE_LOCALE) {
                _applicationLanguage.value = Constants.ARABIC_LANGUAGE_LOCALE
            }
        }
    }

    fun setAppTheme() {
        if (baseUseCase.getAppTheme().value == Constants.DEFAULT_THEME) {
            _applicationTheme.value = R.style.PinkStyle
            _defaultThemeCheckedCheckboxObserve.value = Constants.PINK_CHECKBOX_CHECKED
        } else {
            if (baseUseCase.getAppTheme().value == Constants.THEME_PINK) {
                _applicationTheme.value = R.style.PinkStyle
            } else if (baseUseCase.getAppTheme().value == Constants.THEME_BLUE) {
                _applicationTheme.value = R.style.PinkStyle
            }
        }
    }

    fun refresh() {
        _applicationLanguage.refresh()
        _applicationTheme.refresh()
    }
}


class RefreshableLiveData<T>(
    private val source: () -> LiveData<T>
) : MediatorLiveData<T>() {

    private var liveData = source()

    init {
        this.addSource(liveData, ::observer)
    }

    private fun observer(data: T) {
        value = data
    }

    fun refresh() {
        this.removeSource(liveData)
        liveData = source()
        this.addSource(liveData, ::observer)
    }
}
