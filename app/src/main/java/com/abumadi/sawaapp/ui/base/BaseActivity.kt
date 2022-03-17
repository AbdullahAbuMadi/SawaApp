package com.abumadi.sawaapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.db.SharedPreferencesDb
import com.abumadi.sawaapp.di.component.DaggerAppComponent
import com.abumadi.sawaapp.di.modules.AppModule
import com.abumadi.sawaapp.others.Constants
import java.util.*
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    lateinit var baseViewModel: BaseViewModel

    @Inject
    lateinit var baseViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        daggerComponentSetUp()
        baseViewModelSetUp()
        updateAppThemeAndLanguage()
    }

    private fun daggerComponentSetUp() {
        val component =
            DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.baseActivityInject(this)
    }

    private fun baseViewModelSetUp() {
        baseViewModel = ViewModelProvider(this, baseViewModelFactory).get(BaseViewModel::class.java)
    }

    private fun updateAppThemeAndLanguage() {
        appLanguageObserve()
//        appThemeObserve()
        if (SharedPreferencesDb.getAppTheme(applicationContext) == Constants.DEFAULT_THEME) {
            SharedPreferencesDb.saveThemesChickBoxState(
                applicationContext,
                Constants.PINK_CHECKBOX_CHECKED
            )
            setTheme(R.style.PinkStyle)
        } else {
            if (SharedPreferencesDb.getAppTheme(applicationContext) == Constants.THEME_PINK) {
                setTheme(R.style.PinkStyle)
            } else if (SharedPreferencesDb.getAppTheme(applicationContext) == Constants.THEME_BLUE) {
                setTheme(R.style.BlueStyle)
            }
        }
    }

    private fun appThemeObserve() {
        baseViewModel.setAppTheme()
        defaultThemeCheckedCheckboxObserve()
        baseViewModel.applicationTheme.observe(this, {
            setTheme(it ?: R.style.PinkStyle)
        })
    }

    private fun defaultThemeCheckedCheckboxObserve() {
        baseViewModel.defaultThemeCheckedCheckbox.observe(this, {
            SharedPreferencesDb.saveThemesChickBoxState(this, it)
        })
    }

    private fun appLanguageObserve() {
        baseViewModel.setAppLanguage()
        defaultLanguageCheckedCheckboxObserve()
        baseViewModel.applicationLanguage.observe(this, {
            val locale = Locale(it?:Constants.ENGLISH_LANGUAGE_LOCALE)
            val config = applicationContext.resources.configuration
            config.setLocale(locale)
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        })
    }

    //cuz I need logic to observe once at first
    private fun defaultLanguageCheckedCheckboxObserve() {
        baseViewModel.defaultLanguageCheckedCheckbox.observe(this, {
            SharedPreferencesDb.saveLanguagesChickBoxState(this, it?:Constants.ENG_CHECKBOX_CHECKED)
        })
    }
}

