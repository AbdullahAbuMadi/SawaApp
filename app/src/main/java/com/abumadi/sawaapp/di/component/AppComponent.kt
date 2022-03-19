package com.abumadi.sawaapp.di.component

import com.abumadi.sawaapp.di.modules.AppModule
import com.abumadi.sawaapp.di.modules.ViewModelsModule
import com.abumadi.sawaapp.ui.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelsModule::class, AppModule::class])
interface AppComponent {
    fun activitiesInject(baseActivity: BaseActivity)
}