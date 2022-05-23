package com.abumadi.sawaapp.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.constantsclasses.Destinations
import com.abumadi.sawaapp.data.constantsclasses.Places
import com.abumadi.sawaapp.databinding.ActivityHomeBinding
import com.abumadi.sawaapp.databinding.CheckoutDialogBinding
import com.abumadi.sawaapp.databinding.RatingDialogBinding
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.others.TimerService
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.scanner.ScannerActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlin.math.roundToInt


class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, TextWatcher,
    View.OnTouchListener, View.OnClickListener {

    private val mDrawerLayout: DrawerLayout by lazy {
        findViewById(R.id.drawer)
    }
    private val mNavigationView: NavigationView by lazy {
        findViewById(R.id.nav_view)
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val homeBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private lateinit var blueCheckbox: CompoundButton
    private lateinit var pinkCheckbox: CompoundButton
    private lateinit var arabicCheckbox: CompoundButton
    private lateinit var englishCheckbox: CompoundButton
    private var placeName: String? = null
    private var branchName: String? = null
    private var placeIcon: Int? = null

    private var isFavouriteChecked = false

    //stop watch
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeBinding.root)
        getDataFromQrCodeScanner()
        homeScreenSetUp()
    }

    private fun homeScreenSetUp() {

        navDrawerSetUp()
        toolbarSetUp()
        navViewHeaderClick()
        destinationRecyclerViewSetUp()
        placesRecyclerViewSetUp()
        languageCheckboxesInflate()
        themesCheckboxesInflate()
        mNavigationView.setNavigationItemSelectedListener(this)
        homeBinding.includeBottomSheet.whereToEd.addTextChangedListener(this)
        homeBinding.includeBottomSheet.whereToEd.setOnTouchListener(this)

        if (placeName != null) {
            homeBinding.includeCheckInButton.root.visibility = View.GONE
            homeBinding.includeCheckedInPlace.root.visibility = View.VISIBLE
            timerServiceSetUp()
            startTimer()
            setUpCheckedInPlaceInformation()
            homeBinding.includeCheckedInPlace.checkOutCv.setOnClickListener(this)
            homeBinding.includeCheckedInPlace.addToFavoriteIv.setOnClickListener(this)
            homeBinding.includeCheckedInPlace.checkInAnotherPlace.setOnClickListener(this)
        } else {
            homeBinding.includeCheckInButton.root.visibility = View.VISIBLE
            homeBinding.includeCheckInButton.checkInButton.setOnClickListener(this)
        }
    }

    //suppose to divide on 1000 >>because it is in millis
    //TODO move it to base activity:
    //TODO Solve the dialog problem:done
    //TODO Solve changing theme and language issues:
    //TODO Add Rating Dialog:done
    //TODO Add checkout Dialog:done
    //TODO Add fav click :done

    private fun timerServiceSetUp() {
        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    override fun onDestroy() {
        stopTimer()
        super.onDestroy()
    }

    private fun setUpCheckedInPlaceInformation() {
        homeBinding.includeCheckedInPlace.placeIconIv.setImageResource(placeIcon ?: 0)
        homeBinding.includeCheckedInPlace.checkedInPlaceTv.text = "$placeName"
        homeBinding.includeCheckedInPlace.placeBranchTv.text = "$branchName"
        homeBinding.includeCheckedInPlace.systemTime.text =
            getTimeStringFromDoubleWithoutSeconds(((System.currentTimeMillis() + 10800000) / 1000).toDouble())
    }

    private fun getDataFromQrCodeScanner() {
        placeIcon = intent.getIntExtra("placeIcon", 0)
        placeName = intent.getStringExtra("placeName")
        branchName = intent.getStringExtra("branchName")
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            homeBinding.includeCheckedInPlace.durationCounterTv.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun getTimeStringFromDoubleWithoutSeconds(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60

        return makeTimeStringWithOutSeconds(hours, minutes)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)

    private fun makeTimeStringWithOutSeconds(hour: Int, min: Int): String =
        String.format("%02d:%02d", hour, min)

    override fun onBackPressed() {
        stopTimer()
        finishAffinity()
        super.onBackPressed()
    }

    private fun navDrawerSetUp() {
        mDrawerLayout.setScrimColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )//no shadow
    }

    private fun placesRecyclerViewSetUp() {
        homeBinding.includeBottomSheet.placesRv.adapter =
            PlacesAdapter(this, Places.getPlacesList())
    }

    private fun destinationRecyclerViewSetUp() {
        homeBinding.includeBottomSheet.destinationRv.adapter =
            DestinationsAdapter(this, Destinations.getDestinationsList(this))
        //to solve two recyclerview in same bottomSheet problem
        androidx.core.view.ViewCompat.setNestedScrollingEnabled(
            homeBinding.includeBottomSheet.destinationRv,
            false
        )
    }

    private fun navViewHeaderClick() {
        val headerView = mNavigationView.getHeaderView(0)
        val headerBackButton = headerView.findViewById<ImageButton>(R.id.iv_back_button_header)
        headerBackButton.setOnClickListener {
            mDrawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    private fun toolbarSetUp() {
        setSupportActionBar(homeBinding.includeToolbar.toolbar)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = ""
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_icon -> {
                mDrawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorites_tap -> {
                Toast.makeText(this, "favorites", Toast.LENGTH_SHORT).show()
            }
            R.id.history_tap -> {
                Toast.makeText(this, "history", Toast.LENGTH_SHORT).show()
            }
            R.id.language_tap -> {
                Toast.makeText(this, "language", Toast.LENGTH_SHORT).show()
            }
            R.id.theme_tap -> {
                Toast.makeText(this, "theme", Toast.LENGTH_SHORT).show()
            }
            R.id.logout_tap -> {
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (homeBinding.includeBottomSheet.whereToEd.text?.isNotEmpty() == true) {
            homeBinding.includeBottomSheet.whereToEd.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_remove,
                0
            )
        } else {
            homeBinding.includeBottomSheet.whereToEd.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_search,
                0
            )
        }
    }

    override fun afterTextChanged(viewText: Editable?) {}

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {//if click on remove
            homeBinding.includeBottomSheet.whereToEd.editableText.clear()
        }
        return false
    }

    private fun languageCheckboxesInflate() {
        val languagesMenuItem = mNavigationView.menu.findItem(R.id.languagesCheckBoxesMenuItems)
        englishCheckbox =
            languagesMenuItem.actionView.findViewById(R.id.english_checkbox) as CompoundButton
        arabicCheckbox =
            languagesMenuItem.actionView.findViewById(R.id.arabic_checkbox) as CompoundButton
        englishCheckbox.setOnClickListener(this)
        arabicCheckbox.setOnClickListener(this)
        languagesCheckboxesBehaviorHandleObserve(arabicCheckbox, englishCheckbox)
    }

    private fun languagesCheckboxesBehaviorHandleObserve(
        arabicCheckbox: CompoundButton,
        englishCheckbox: CompoundButton
    ) {
        homeViewModel.languageCheckboxesBehavior()
        homeViewModel.isEnglishCheckboxChecked.observe(this) {
            englishCheckbox.isChecked = true
        }

        homeViewModel.isArabicCheckboxChecked.observe(this) {
            arabicCheckbox.isChecked = true
        }
    }

    private fun themesCheckboxesInflate() {
        val themesMenuItem = mNavigationView.menu.findItem(R.id.themesCheckBoxesMenuItems)
        blueCheckbox = themesMenuItem.actionView.findViewById(R.id.blue_checkbox) as CompoundButton
        pinkCheckbox = themesMenuItem.actionView.findViewById(R.id.pink_checkbox) as CompoundButton
        this.blueCheckbox.setOnClickListener(this)
        this.pinkCheckbox.setOnClickListener(this)

        themesCheckboxesBehaviorHandleObserve(blueCheckbox, pinkCheckbox)
    }

    private fun themesCheckboxesBehaviorHandleObserve(
        blueCheckbox: CompoundButton,
        pinkCheckbox: CompoundButton
    ) {
        homeViewModel.themeCheckboxesBehavior()
        homeViewModel.isBlueCheckboxChecked.observe(this) {
            blueCheckbox.isChecked = true
            homeBinding.includeCheckInButton.checkInButton.setImageResource(R.drawable.button_icon_blue)
            homeBinding.includeToolbar.toolbarLogo.setImageResource(R.drawable.sawa_logo_blue)
        }

        homeViewModel.isPinkCheckboxChecked.observe(this) {
            pinkCheckbox.isChecked = true
            homeBinding.includeCheckInButton.checkInButton.setImageResource(R.drawable.button_icon_pink)
            homeBinding.includeToolbar.toolbarLogo.setImageResource(R.drawable.sawa_logo_pink)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.english_checkbox -> {
                sharedPreference.saveLanguagesCheckBoxState(
                    applicationContext, Constants.ENG_CHECKBOX_CHECKED
                )
                sharedPreference.setAppLanguage(
                    applicationContext,
                    Constants.ENGLISH_LANGUAGE_LOCALE
                )
                recreateActivity()
            }

            R.id.arabic_checkbox -> {
                sharedPreference.saveLanguagesCheckBoxState(

                    applicationContext, Constants.ARAB_CHECKBOX_CHECKED
                )
                sharedPreference.setAppLanguage(
                    applicationContext,
                    Constants.ARABIC_LANGUAGE_LOCALE
                )
                recreateActivity()
            }

            R.id.blue_checkbox -> {
                sharedPreference.saveThemesCheckBoxState(

                    applicationContext, Constants.BLUE_CHECKBOX_CHECKED
                )
                sharedPreference.setAppTheme(applicationContext, Constants.THEME_BLUE)
                recreateActivity()

            }
            R.id.pink_checkbox -> {
                sharedPreference.saveThemesCheckBoxState(

                    applicationContext, Constants.PINK_CHECKBOX_CHECKED
                )
                sharedPreference.setAppTheme(applicationContext, Constants.THEME_PINK)
                recreateActivity()
            }
            R.id.check_in_button -> {
                startActivity(Intent(this, ScannerActivity::class.java))
            }

            R.id.check_out_cv -> {
                stopTimer()

                val checkoutDialogBinding = CheckoutDialogBinding.bind(
                    layoutInflater.inflate(
                        R.layout.checkout_dialog,
                        null
                    )
                )
                val checkoutDialogBuilder =
                    MaterialAlertDialogBuilder(this@HomeActivity, R.style.MyThemeOverlayAlertDialog)
                checkoutDialogBuilder.setView(checkoutDialogBinding.root)
                val checkoutDialog = checkoutDialogBuilder.create()
                //rating dialog
                val ratingDialogBinding = RatingDialogBinding.bind(
                    layoutInflater.inflate(
                        R.layout.rating_dialog,
                        null
                    )
                )
                val ratingDialogBuilder =
                    MaterialAlertDialogBuilder(this@HomeActivity, R.style.MyThemeOverlayAlertDialog)
                ratingDialogBuilder.setView(ratingDialogBinding.root)
                val ratingDialog = ratingDialogBuilder.create()

                ratingDialogBinding.ratingIv.setImageResource(placeIcon ?: 0)
                ratingDialogBinding.placeNameTvDialogRating.text = placeName
                ratingDialogBinding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, b ->
                    ratingDialogBinding.saveBtnDialog.setTextColor(Color.parseColor("#FFFFFF"))
                    ratingDialogBinding.saveBtnDialog.setBackgroundColor(Color.parseColor("#599EF0"))
                    ratingDialogBinding.saveBtnDialog.setOnClickListener {
                        ratingDialog.dismiss()
                        Toast.makeText(
                            this@HomeActivity,
                            "You have rated $placeName with : " + ratingDialogBinding.ratingBar.rating.toInt()
                                .toString() + " stars",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                ratingDialogBinding.maybeLaterDialog.setOnClickListener {
                    ratingDialog.dismiss()
                }
                checkoutDialogBinding.checkoutIv.setImageResource(placeIcon ?: 0)
                checkoutDialogBinding.placeNameTvDialog.text = "$placeName"
                checkoutDialogBinding.branchNameTvDialog.text = "$branchName"
                checkoutDialogBinding.spentTimeDialog.text =
                    homeBinding.includeCheckedInPlace.durationCounterTv.text.toString()
                checkoutDialog.show()
                checkoutDialogBinding.checkoutBtnDialog.setOnClickListener {
                    checkoutDialog.dismiss()
                    ratingDialog.show()
                    Toast.makeText(this@HomeActivity, "checked_out", Toast.LENGTH_SHORT).show()
                }

                checkoutDialogBinding.cancelDialog.setOnClickListener {
                    checkoutDialog.dismiss()
                }
            }

            R.id.add_to_favorite_iv -> {
                isFavouriteChecked = if (isFavouriteChecked) {
                    homeBinding.includeCheckedInPlace.addToFavoriteIv.setImageResource(R.drawable.ic_heart)
                    false
                } else {
                    homeBinding.includeCheckedInPlace.addToFavoriteIv.setImageResource(R.drawable.ic_heart_filled)
                    true
                }
            }

            R.id.check_in_another_place -> {
                startActivity(Intent(this, ScannerActivity::class.java))
                stopTimer()
            }
        }
    }
}
