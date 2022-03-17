package com.abumadi.sawaapp.di.modules

import android.app.Application
import android.content.Context
import com.abumadi.sawaapp.data.source.AppRepository
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.domain.SplashUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule @Inject constructor(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppRepository(
    ) = AppRepository(context)


    @Singleton
    @Provides
    fun provideSplashUseCase(
        appRepository: AppRepository
    ) = SplashUseCase(appRepository)


    @Singleton
    @Provides
    fun provideBaseUseCase(
        appRepository: AppRepository
    ) = BaseUseCase(appRepository)

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideApplication(
    ) = Application()
}
