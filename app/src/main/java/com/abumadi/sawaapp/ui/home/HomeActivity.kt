package com.abumadi.sawaapp.ui.home

import android.content.Intent
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
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.constantsclasses.Destinations
import com.abumadi.sawaapp.data.constantsclasses.Places
import com.abumadi.sawaapp.databinding.CheckoutDialogBinding
import com.abumadi.sawaapp.databinding.RatingDialogBinding
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.scanner.ScannerActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView


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

    //checkout dialog binding
    private val checkoutDialogBinding: CheckoutDialogBinding by lazy {
        CheckoutDialogBinding.bind(
            layoutInflater.inflate(
                R.layout.checkout_dialog,
                null
            )
        )
    }

    //rating dialog binding
    private val ratingDialogBinding by lazy {
        RatingDialogBinding.bind(
            layoutInflater.inflate(
                R.layout.rating_dialog,
                null
            )
        )
    }
    private lateinit var ratingDialog: AlertDialog
    private lateinit var checkoutDialog: AlertDialog
    private lateinit var blueCheckbox: CompoundButton
    private lateinit var pinkCheckbox: CompoundButton
    private lateinit var arabicCheckbox: CompoundButton
    private lateinit var englishCheckbox: CompoundButton

    private var isFavouriteChecked = false


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

        if (sharedPreference.getAppCurrentUi(this) == Constants.CHECK_IN_LAYOUT_UI) {
            homeBinding.includeCheckInButton.root.visibility = View.GONE
            homeBinding.includeCheckedInPlace.root.visibility = View.VISIBLE
            homeBinding.includeCheckedInPlace.checkOutCv.setOnClickListener(this)
            homeBinding.includeCheckedInPlace.addToFavoriteIv.setOnClickListener(this)
            homeBinding.includeCheckedInPlace.checkInAnotherPlace.setOnClickListener(this)
            checkoutDialogBinding.checkoutBtnDialog.setOnClickListener(this)
            ratingDialogBinding.maybeLaterDialog.setOnClickListener(this)
            checkoutDialogBinding.cancelDialog.setOnClickListener(this)
            timerServiceSetUp()
            startTimer()
            setUpCheckedInPlaceInformation()
            setUpCheckoutDialog()
            setUpRatingDialog()
        } else if (sharedPreference.getAppCurrentUi(this) == Constants.BUTTON_UI) {
            homeBinding.includeCheckInButton.root.visibility = View.VISIBLE
            homeBinding.includeCheckInButton.checkInButton.setOnClickListener(this)
        }
    }

    private fun setUpCheckedInPlaceInformation() {
        homeBinding.includeCheckedInPlace.placeIconIv.setImageResource(
            sharedPreference.getCheckedInInfo(
                this
            )?.placeIcon ?: 0
        )
        homeBinding.includeCheckedInPlace.checkedInPlaceTv.text =
            "${sharedPreference.getCheckedInInfo(this)?.placeName}"
        homeBinding.includeCheckedInPlace.placeBranchTv.text =
            "${sharedPreference.getCheckedInInfo(this)?.branchName}"
        homeBinding.includeCheckedInPlace.systemTime.text =
            getTimeStringFromDoubleWithoutSeconds(((System.currentTimeMillis() + 10800000) / 1000).toDouble())
    }

    private fun getDataFromQrCodeScanner() {
        checkInInfo = intent.getParcelableExtra("checkedInInfo")
        placeName = checkInInfo?.placeName
        placeIcon = checkInInfo?.placeIcon
        branchName = checkInInfo?.branchName
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
                if (timerStarted) stopTimer()
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
                if (timerStarted) stopTimer()
                recreateActivity()
            }

            R.id.blue_checkbox -> {
                sharedPreference.saveThemesCheckBoxState(

                    applicationContext, Constants.BLUE_CHECKBOX_CHECKED
                )
                sharedPreference.setAppTheme(applicationContext, Constants.THEME_BLUE)
                if (timerStarted) stopTimer()
                recreateActivity()
            }
            R.id.pink_checkbox -> {
                sharedPreference.saveThemesCheckBoxState(

                    applicationContext, Constants.PINK_CHECKBOX_CHECKED
                )
                sharedPreference.setAppTheme(applicationContext, Constants.THEME_PINK)
                if (timerStarted) stopTimer()
                recreateActivity()
            }
            R.id.check_in_button -> {
                startActivity(Intent(this, ScannerActivity::class.java))
            }

            R.id.check_out_cv -> {
                checkoutDialogBinding.spentTimeDialog.text =
                    homeBinding.includeCheckedInPlace.durationCounterTv.text
                checkoutDialog.show()
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
            R.id.checkout_btn_dialog -> {
                stopTimer()
                checkoutDialog.cancel()
                ratingDialog.show()
                Toast.makeText(this@HomeActivity, "checked_out", Toast.LENGTH_SHORT).show()
            }

            R.id.save_btn_dialog -> {
                ratingDialog.dismiss()
                Toast.makeText(
                    this@HomeActivity,
                    "You have rated ${sharedPreference.getCheckedInInfo(this)?.placeName} with : " + ratingDialogBinding.ratingBar.rating.toInt()
                        .toString() + " stars",
                    Toast.LENGTH_LONG
                ).show()
                sharedPreference.setAppCurrentUi(applicationContext, Constants.BUTTON_UI)
                stopTimer()
                recreateActivity()
            }

            R.id.maybe_later_dialog -> {
                ratingDialog.dismiss()
            }

            R.id.cancel_dialog -> {
                checkoutDialog.dismiss()
            }
        }
    }

    private fun setUpRatingDialog() {
        ratingDialog = setUpRatingDialogBuilder().create()
        ratingDialogBinding.ratingIv.setImageResource(
            sharedPreference.getCheckedInInfo(this)?.placeIcon ?: 0
        )
        ratingDialogBinding.placeNameTvDialogRating.text =
            "${sharedPreference.getCheckedInInfo(this)?.placeName}"
        ratingDialogBinding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, b ->
            ratingDialogBinding.saveBtnDialog.setOnClickListener(this)
            ratingDialogBinding.saveBtnDialog.setTextColor(Color.parseColor("#FFFFFF"))
            ratingDialogBinding.saveBtnDialog.setBackgroundColor(Color.parseColor("#599EF0"))
        }
    }

    private fun setUpCheckoutDialog() {
        checkoutDialog = setUpCheckoutDialogBuilder().create()
        checkoutDialogBinding.checkoutIv.setImageResource(
            sharedPreference.getCheckedInInfo(this)?.placeIcon ?: 0
        )
        checkoutDialogBinding.placeNameTvDialog.text =
            "${sharedPreference.getCheckedInInfo(this)?.placeName}"
        checkoutDialogBinding.branchNameTvDialog.text =
            "${sharedPreference.getCheckedInInfo(this)?.branchName}"
    }

    private fun setUpRatingDialogBuilder(): MaterialAlertDialogBuilder {
        val ratingDialogBuilder =
            MaterialAlertDialogBuilder(this@HomeActivity, R.style.MyThemeOverlayAlertDialog)
        ratingDialogBuilder.setView(ratingDialogBinding.root)
        return ratingDialogBuilder
    }

    private fun setUpCheckoutDialogBuilder(): MaterialAlertDialogBuilder {
        val checkoutDialogBuilder =
            MaterialAlertDialogBuilder(this@HomeActivity, R.style.MyThemeOverlayAlertDialog)
        checkoutDialogBuilder.setView(checkoutDialogBinding.root)
        return checkoutDialogBuilder
    }
}
