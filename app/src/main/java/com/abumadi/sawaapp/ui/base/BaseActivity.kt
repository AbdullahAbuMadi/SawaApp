package com.abumadi.sawaapp.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.source.CheckedInInfo
import com.abumadi.sawaapp.databinding.ActivityHomeBinding
import com.abumadi.sawaapp.di.component.DaggerAppComponent
import com.abumadi.sawaapp.di.modules.AppModule
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.sharedpreference.SharedPreferencesManager
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

open class BaseActivity : AppCompatActivity() {

    private lateinit var baseViewModel: BaseViewModel

    @Inject
    lateinit var sharedPreference: SharedPreferencesManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //stop watch
    var timerStarted = false

    var placeName: String? = null
    var branchName: String? = null
    var placeIcon: String? = null
    var duration: Double? = null
    var checkInTime: String? = null

    val homeBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    var checkedInInfo: CheckedInInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        daggerComponentSetUp()
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(BaseViewModel::class.java)
        duration = sharedPreference.getCheckedInInfo(applicationContext)?.duration
        checkInTime = sharedPreference.getCheckedInInfo(applicationContext)?.checkInTime
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
        setUpCurrentUi()
    }

    private fun setUpCurrentUi() {
        baseViewModel.setAppCurrentUi()
        val defaultUi = baseViewModel.getDefaultUi()
        defaultUi(defaultUi)
        val currentUi = baseViewModel.getCurrentUi()
        sharedPreference.setAppCurrentUi(applicationContext, currentUi ?: "")
    }

    private fun defaultUi(defaultUi: Boolean?) {
        if (defaultUi == true) {
            sharedPreference.setAppCurrentUi(applicationContext, Constants.BUTTON_UI)
        }
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
            sharedPreference.saveThemesCheckBoxState(
                applicationContext,
                Constants.PINK_CHECKBOX_CHECKED
            )
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
        config.setLocale(Locale(locale, "MA"))
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
        saveCheckInInfo()

        val intent = Intent(this, this::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun saveCheckInInfo() {
        placeName = sharedPreference.getCheckedInInfo(applicationContext)?.placeName
        placeIcon = sharedPreference.getCheckedInInfo(applicationContext)?.placeIcon
        branchName = sharedPreference.getCheckedInInfo(applicationContext)?.branchName
        stopTimer()
        checkedInInfo = CheckedInInfo(
            placeName ?: "",
            placeIcon ?: "",
            branchName ?: "",
            duration ?: 0.0,
            checkInTime ?: ""
        )
        sharedPreference.saveCheckedInInfo(applicationContext, checkedInInfo)
    }

    fun startTimer() {
        baseViewModel.startTimer(duration)
        lifecycleScope.launch {
            baseViewModel.timerStateFlow.collect {
                homeBinding.includeCheckedInPlace.durationCounterTv.text =
                    getTimeStringFromDouble(it)
            }
        }
    }

    fun stopTimer() {
        baseViewModel.stopTimer()
        baseViewModel.lastTimeValue.observe(this) {
            duration = it
        }
        homeBinding.includeCheckedInPlace.durationCounterTv.text =
            getTimeStringFromDouble(duration)
    }

    private fun getTimeStringFromDouble(time: Double?): String {
        val resultInt = time?.roundToInt()
        val hours = resultInt!! % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format(" %02d :%02d :%02d", hour, min, sec)

    override fun onBackPressed() {
        saveCheckInInfo()
        finishAffinity()
        super.onBackPressed()
    }
}

