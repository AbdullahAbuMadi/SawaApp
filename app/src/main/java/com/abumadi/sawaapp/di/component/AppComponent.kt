package com.abumadi.sawaapp.di.component

import com.abumadi.sawaapp.di.modules.AppModule
import com.abumadi.sawaapp.di.modules.ViewModelsModule
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelsModule::class, AppModule::class])
interface AppComponent {
    fun splashActivityInject(splashActivity: SplashActivity)
    fun baseActivityInject(baseActivity: BaseActivity)
}