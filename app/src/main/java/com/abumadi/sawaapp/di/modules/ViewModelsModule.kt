package com.abumadi.sawaapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.others.viewmodelfactory.ViewModelFactory
import com.abumadi.sawaapp.others.viewmodelfactory.ViewModelKey
import com.abumadi.sawaapp.ui.base.BaseViewModel
import com.abumadi.sawaapp.ui.home.HomeViewModel
import com.abumadi.sawaapp.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule() {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)//key
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)//key
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    /////////
    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)//key
    internal abstract fun baseViewModel(viewModel: BaseViewModel): ViewModel

}