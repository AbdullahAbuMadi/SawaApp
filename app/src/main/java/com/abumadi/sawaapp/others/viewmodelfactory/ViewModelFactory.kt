package com.abumadi.sawaapp.others.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (viewModels[modelClass]?.get() == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
        return viewModels[modelClass]?.get() as T
    }
}