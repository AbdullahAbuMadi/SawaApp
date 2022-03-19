package com.abumadi.sawaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.HomeUseCase
import com.abumadi.sawaapp.others.Constants
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _isBlueCheckboxChecked = MutableLiveData<Boolean>()
    private val _isPinkCheckboxChecked = MutableLiveData<Boolean>()

    private val _isArabicCheckboxChecked = MutableLiveData<Boolean>()
    private val _isEnglishCheckboxChecked = MutableLiveData<Boolean>()

    private val _toolbarLogo = MutableLiveData<Int>()
    private val _checkInButton = MutableLiveData<Int>()


    val isBlueCheckboxChecked: LiveData<Boolean>
        get() = _isBlueCheckboxChecked

    val isPinkCheckboxChecked: LiveData<Boolean>
        get() = _isPinkCheckboxChecked

    val toolbarLogo: LiveData<Int>
        get() = _toolbarLogo

    val checkInButton: LiveData<Int>
        get() = _checkInButton


    fun themeCheckboxesBehavior() {
        if (homeUseCase.themeCheckboxExecuted(Constants.BLUE_CHECKBOX_CHECKED)) {
            _isBlueCheckboxChecked.value = true
            _checkInButton.value = R.drawable.button_icon_blue
            _toolbarLogo.value = R.drawable.sawa_logo_blue
        } else if (homeUseCase.themeCheckboxExecuted(Constants.PINK_CHECKBOX_CHECKED)) {
            _isPinkCheckboxChecked.value = true
            _checkInButton.value = R.drawable.button_icon_pink
            _toolbarLogo.value = R.drawable.sawa_logo_pink
        }
    }

    fun languageCheckboxesBehavior() {
        if (homeUseCase.themeCheckboxExecuted(Constants.ENG_CHECKBOX_CHECKED)) {
            _isEnglishCheckboxChecked.value = true
        } else if (homeUseCase.themeCheckboxExecuted(Constants.ARAB_CHECKBOX_CHECKED)) {
            _isArabicCheckboxChecked.value = true
        }
    }

}