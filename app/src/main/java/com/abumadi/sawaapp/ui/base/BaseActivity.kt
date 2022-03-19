package com.abumadi.sawaapp.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.db.SharedPreferencesDb
import com.abumadi.sawaapp.di.component.DaggerAppComponent
import com.abumadi.sawaapp.di.modules.AppModule
import java.util.*
import javax.inject.Inject
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.others.Constants

//TODO con not make view model cuz setTheme function didn't accept to set inside live data observer
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var db: SharedPreferencesDb

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        daggerComponentSetUp()
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
        if (db.getAppTheme(applicationContext) == Constants.DEFAULT_THEME) {
            defaultThemeCheckedCheckbox()
            setTheme(R.style.PinkStyle)
        } else {
            if (db.getAppTheme(applicationContext) == Constants.THEME_PINK) {
                setTheme(R.style.PinkStyle)
            } else if (db.getAppTheme(applicationContext) == Constants.THEME_BLUE) {
                setTheme(R.style.BlueStyle)
            }
        }
    }

    private fun defaultThemeCheckedCheckbox() {
        db.saveThemesChickBoxState(applicationContext, Constants.PINK_CHECKBOX_CHECKED)
    }

    private fun setUpApplicationLanguage() {
        if (db.getAppLanguage(applicationContext) == Constants.DEFAULT_LANGUAGE) {
            defaultLanguageCheckedCheckbox()
            setLanguage(Constants.ENGLISH_LANGUAGE_LOCALE)

        } else {
            if (db.getAppLanguage(applicationContext) == Constants.ENGLISH_LANGUAGE_LOCALE) {
                setLanguage(Constants.ENGLISH_LANGUAGE_LOCALE)

            } else if (db.getAppLanguage(applicationContext) == Constants.ARABIC_LANGUAGE_LOCALE) {
                setLanguage(Constants.ARABIC_LANGUAGE_LOCALE)
            }
        }
    }

    private fun setLanguage(locale: String) {
        val config = applicationContext.resources.configuration
        config.setLocale(Locale(locale))
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    private fun defaultLanguageCheckedCheckbox() {
        db.saveLanguagesChickBoxState(
            applicationContext, Constants.ENG_CHECKBOX_CHECKED
        )
    }

    //recreate the activity
    fun recreateActivity() {
        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()

        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}

