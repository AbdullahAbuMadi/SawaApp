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

//TODO can not make observe live data from view model cuz setTheme function didn't accept to set inside live data observer
open class BaseActivity : AppCompatActivity() {

    lateinit var baseViewModel: BaseViewModel

    @Inject
    lateinit var db: SharedPreferencesDb

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
            db.saveThemesChickBoxState(applicationContext, Constants.PINK_CHECKBOX_CHECKED)
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
            db.saveLanguagesChickBoxState(
                applicationContext, Constants.ENG_CHECKBOX_CHECKED
            )
        }
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

//      baseViewModel.setAppTheme()
//        baseViewModel.currentTheme.observe(this, {
//            this.setTheme(it)
////            baseViewModel.refresh()
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
//            Log.d("baseActivity", "setUpApplicationTheme:$it")
//        })

