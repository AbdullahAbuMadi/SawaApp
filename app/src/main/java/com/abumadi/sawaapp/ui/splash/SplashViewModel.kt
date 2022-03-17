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

    private val _navigate = SingleLiveEvent<Boolean>()
    private val _currentSplashLogo = SingleLiveEvent<Int>()

    val navigate: LiveData<Boolean>
        get() = _navigate

    val currentSplashLogo: LiveData<Int>
        get() = _currentSplashLogo

    fun navigate() {
        viewModelScope.launch {
            delay(2_000)
            _navigate.value = true
        }
    }

    fun setCurrentSplashLogo() {
        if (splashUseCase.getAppTheme().value == Constants.THEME_PINK) {
            _currentSplashLogo.value = R.drawable.ic_sawa_logo_pink
        } else if (splashUseCase.getAppTheme().value == Constants.THEME_BLUE) {
            _currentSplashLogo.value = R.drawable.ic_sawa_logo_blue
        }
    }
}