package com.abumadi.sawaapp.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.sharedpreference.SharedPreferencesManager
import com.abumadi.sawaapp.di.component.DaggerAppComponent
import com.abumadi.sawaapp.di.modules.AppModule
import java.util.*
import javax.inject.Inject
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.others.Constants

//TODO can not make observe live data from view model cuz setTheme function didn't accept to set inside live data observer
open class BaseActivity : AppCompatActivity() {

    lateinit var baseViewModel: BaseViewModel

    @Inject
    lateinit var sharedPreference: SharedPreferencesManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        daggerComponentSetUp()
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(BaseViewModel::class.java)
        updateAppThemeAndLanguage()
    }

    private fun daggerComponentSetUp() {
        val component =
            DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
        component.activitiesInject(this@BaseActivity)
    }

    private fun updateAppThemeAndLanguage() {
        setUpApplicationTheme()
        setUpApplicationLanguage()
    }

    private fun setUpApplicationTheme() {
        baseViewModel.setAppTheme()
        val defaultTheme = baseViewModel.getDefaultTheme()
        defaultThemeCheckedCheckbox(defaultTheme)
        val currentTheme = baseViewModel.getCurrentTheme()
        setTheme(currentTheme ?: 0)
    }

    private fun defaultThemeCheckedCheckbox(defaultTheme: Boolean?) {
        if (defaultTheme == true)
            sharedPreference.saveThemesCheckBoxState(applicationContext, Constants.PINK_CHECKBOX_CHECKED)
    }

    private fun setUpApplicationLanguage() {
        baseViewModel.setAppLanguage()
        val defaultLanguage = baseViewModel.getDefaultLanguage()
        defaultLanguageCheckedCheckbox(defaultLanguage)
        val currentLanguage = baseViewModel.getCurrentLanguage()
        setLanguage(currentLanguage ?: "")
    }

    private fun setLanguage(locale: String) {
        val config = applicationContext.resources.configuration
        config.setLocale(Locale(locale))
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    private fun defaultLanguageCheckedCheckbox(defaultLanguage: Boolean?) {
        if (defaultLanguage == true) {
            sharedPreference.saveLanguagesCheckBoxState(
                applicationContext, Constants.ENG_CHECKBOX_CHECKED
            )
        }
    }

    //recreate the activity
    fun recreateActivity() {
        val intent = Intent(this,this::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}

