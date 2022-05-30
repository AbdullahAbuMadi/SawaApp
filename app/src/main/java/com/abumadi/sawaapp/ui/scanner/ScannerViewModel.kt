package com.abumadi.sawaapp.ui.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abumadi.sawaapp.api.responses.CheckedInInfoResponse
import com.abumadi.sawaapp.domain.ScannerUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScannerViewModel @Inject constructor(private val scannerUseCase: ScannerUseCase) :
    ViewModel() {

    private val _checkedInInfo = MutableLiveData<CheckedInInfoResponse>()

    val checkedInInfo: LiveData<CheckedInInfoResponse>
        get() = _checkedInInfo

    fun getCheckedInInfo(url: String) {
        viewModelScope.launch {
            _checkedInInfo.value = scannerUseCase.checkedInInfoExecute(url)
        }
    }
}