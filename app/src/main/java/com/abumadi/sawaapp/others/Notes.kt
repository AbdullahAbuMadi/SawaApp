package com.abumadi.sawaapp.others

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

//Locale.setDefault(locale)

// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//check if sdk >lollipop
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            }


//to provide the context
//    @Singleton
//    @Provides
//    fun provideContext(): Context {
//        return context
//    }


//    @Singleton
//    @Provides
//    fun provideApplication(
//    ) = Application()


//
//class RefreshableLiveData<T>(
//    private val source: () -> LiveData<T>
//) : MediatorLiveData<T>() {
//
//    private var liveData = source()
//
//    init {
//        this.addSource(liveData, ::observer)
//    }
//
//    private fun observer(data: T) {
//        value = data
//    }
//
//    fun refresh() {
//        this.removeSource(liveData)
//        liveData = source()
//        this.addSource(liveData, ::observer)
//    }

/**
 * I faced a problem:checkboxes with setOn changed listener and recreate activity>>infinite loop
 * solved>> use on click instead
 * **/