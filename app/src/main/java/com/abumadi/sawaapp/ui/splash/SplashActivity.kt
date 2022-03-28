package com.abumadi.sawaapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.abumadi.sawaapp.databinding.ActivitySplashBinding
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.home.HomeActivity
import kotlinx.coroutines.delay

class SplashActivity : BaseActivity() {

    private lateinit var splashViewModel: SplashViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashViewModelSetUp()
        currentSplashLogoObserve()
        navigateToHomeActivity()
    }

    private fun splashViewModelSetUp() {
        splashViewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    private fun currentSplashLogoObserve() {
        splashViewModel.setCurrentSplashLogo()
        splashViewModel.currentSplashLogo.observe(this, {
            binding.splashSawaLogo.setImageResource(it)
        })
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        lifecycleScope.launchWhenStarted {
            delay(2_000)
            startActivity(intent)
        }
    }
}