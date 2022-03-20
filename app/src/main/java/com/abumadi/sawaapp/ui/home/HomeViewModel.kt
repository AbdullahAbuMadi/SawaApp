package com.abumadi.sawaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abumadi.sawaapp.domain.HomeUseCase
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.others.util.SingleLiveEvent
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _isBlueCheckboxChecked = SingleLiveEvent<Boolean>()
    private val _isPinkCheckboxChecked = SingleLiveEvent<Boolean>()

    private val _isArabicCheckboxChecked = MutableLiveData<Boolean>()
    private val _isEnglishCheckboxChecked = MutableLiveData<Boolean>()

    val isBlueCheckboxChecked: LiveData<Boolean>
        get() = _isBlueCheckboxChecked

    val isPinkCheckboxChecked: LiveData<Boolean>
        get() = _isPinkCheckboxChecked

    val isArabicCheckboxChecked: LiveData<Boolean>
        get() = _isArabicCheckboxChecked

    val isEnglishCheckboxChecked: LiveData<Boolean>
        get() = _isEnglishCheckboxChecked


    fun themeCheckboxesBehavior() {
        if (homeUseCase.themeCheckboxStateExecuted(Constants.BLUE_CHECKBOX_CHECKED)) {
            _isBlueCheckboxChecked.value = true
        } else if (homeUseCase.themeCheckboxStateExecuted(Constants.PINK_CHECKBOX_CHECKED)) {
            _isPinkCheckboxChecked.value = true
        }
    }

    fun languageCheckboxesBehavior() {
        if (homeUseCase.languageCheckboxStateExecuted(Constants.ENG_CHECKBOX_CHECKED)) {
            _isEnglishCheckboxChecked.value = true
        } else if (homeUseCase.languageCheckboxStateExecuted(Constants.ARAB_CHECKBOX_CHECKED)) {
            _isArabicCheckboxChecked.value = true
        }
    }
}