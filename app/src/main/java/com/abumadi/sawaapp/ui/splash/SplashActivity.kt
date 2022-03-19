package com.abumadi.sawaapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModelSetUp()
        navigateObserve()
        currentSplashLogoObserve()
    }

    private fun splashViewModelSetUp() {
        splashViewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    private fun currentSplashLogoObserve() {
        splashViewModel.setCurrentSplashLogo()
        splashViewModel.currentSplashLogo.observe(this, {
            splash_sawa_logo.setImageResource(it)
        })
    }

    private fun navigateObserve() {
        val intent = Intent(this, HomeActivity::class.java)
        splashViewModel.navigate()
        splashViewModel.navigate.observe(this, {
            startActivity(intent)
        })
    }
}