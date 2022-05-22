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
//}

//
//    private val _currentTheme = SingleLiveEvent<Int>()

//    val currentTheme: LiveData<Int>
//        get() = _currentTheme

//livedata
//    fun setAppTheme() {
//        if (baseUseCase.appThemeExecute() == Constants.DEFAULT_THEME || baseUseCase.appThemeExecute() == Constants.THEME_PINK) {
//            _currentTheme.value = R.style.PinkStyle
//        } else if (baseUseCase.appThemeExecute() == Constants.THEME_BLUE) {
//            _currentTheme.value = R.style.BlueStyle
//        }
//    }


//      baseViewModel.setAppTheme()
//        baseViewModel.currentTheme.observe(this, {
//            this.setTheme(it)
////            baseViewModel.refresh()
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
//            Log.d("baseActivity", "setUpApplicationTheme:$it")
//        })
/**
 * I faced a problem:checkboxes with setOn changed listener and recreate activity>>infinite loop
 * solved>> use on click instead
 * **/