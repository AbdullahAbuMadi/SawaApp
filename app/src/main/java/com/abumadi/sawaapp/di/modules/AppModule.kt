package com.abumadi.sawaapp.di.modules

import android.content.Context
import com.abumadi.sawaapp.api.remote.ApiService
import com.abumadi.sawaapp.data.source.AppRepository
import com.abumadi.sawaapp.sharedpreference.SharedPreferencesManager
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.domain.HomeUseCase
import com.abumadi.sawaapp.domain.ScannerUseCase
import com.abumadi.sawaapp.domain.SplashUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppRepository(
        sharedPreference: SharedPreferencesManager,
        apiService: ApiService
    ) = AppRepository(sharedPreference, context, apiService)


    @Singleton
    @Provides
    fun provideSplashUseCase(
        appRepository: AppRepository
    ) = SplashUseCase(appRepository)

    @Singleton
    @Provides
    fun provideHomeUseCase(
        appRepository: AppRepository
    ) = HomeUseCase(appRepository)

    ////////////
    @Singleton
    @Provides
    fun provideBaseUseCase(
        appRepository: AppRepository
    ) = BaseUseCase(appRepository)

    @Singleton
    @Provides
    fun provideScannerUseCase(
        appRepository: AppRepository
    ) = ScannerUseCase(appRepository)


    @Singleton
    @Provides
    fun provideSharedPreferenceDb(
    ) = SharedPreferencesManager()

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}
