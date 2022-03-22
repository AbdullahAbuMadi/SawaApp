package com.abumadi.sawaapp.di.modules

import android.content.Context
import com.abumadi.sawaapp.data.source.AppRepository
import com.abumadi.sawaapp.db.SharedPreferencesDb
import com.abumadi.sawaapp.domain.BaseUseCase
import com.abumadi.sawaapp.domain.HomeUseCase
import com.abumadi.sawaapp.domain.SplashUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppRepository(
        db: SharedPreferencesDb
    ) = AppRepository(db, context)


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
    fun provideSharedPreferenceDb(
    ) = SharedPreferencesDb()

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}
