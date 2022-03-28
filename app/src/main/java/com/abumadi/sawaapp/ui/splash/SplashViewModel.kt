package com.abumadi.sawaapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.domain.SplashUseCase
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.others.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val splashUseCase: SplashUseCase) : ViewModel() {

    private val _currentSplashLogo = SingleLiveEvent<Int>()

    val currentSplashLogo: LiveData<Int>
        get() = _currentSplashLogo

    fun setCurrentSplashLogo() {
        if (splashUseCase.appThemeExecute() == Constants.THEME_PINK) {
            _currentSplashLogo.value = R.drawable.ic_sawa_logo_pink
        } else if (splashUseCase.appThemeExecute() == Constants.THEME_BLUE) {
            _currentSplashLogo.value = R.drawable.ic_sawa_logo_blue
        }
    }
}