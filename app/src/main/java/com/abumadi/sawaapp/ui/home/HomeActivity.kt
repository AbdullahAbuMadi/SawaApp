package com.abumadi.sawaapp.ui.home

import android.content.Intent
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
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.scanner.ScannerActivity
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

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private lateinit var blueCheckbox: CompoundButton
    private lateinit var pinkCheckbox: CompoundButton
    private lateinit var arabicCheckbox: CompoundButton
    private lateinit var englishCheckbox: CompoundButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navDrawerSetUp()
        toolbarSetUp()
        navViewHeaderClick()
        destinationRecyclerViewSetUp()
        placesRecyclerViewSetUp()
        languageCheckboxesInflate()
        themesCheckboxesInflate()
        mNavigationView.setNavigationItemSelectedListener(this)
        binding.includeBottomSheet.whereToEd.addTextChangedListener(this)
        binding.checkInButton.setOnClickListener(this)
        binding.includeBottomSheet.whereToEd.setOnTouchListener(this)
    }

    override fun onBackPressed() {
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
        binding.includeBottomSheet.placesRv.adapter = PlacesAdapter(this, Places.getPlacesList())
    }

    private fun destinationRecyclerViewSetUp() {
        binding.includeBottomSheet.destinationRv.adapter =
            DestinationsAdapter(this, Destinations.getDestinationsList(this))
        //to solve two recyclerview in same bottomSheet problem
        androidx.core.view.ViewCompat.setNestedScrollingEnabled(
            binding.includeBottomSheet.destinationRv,
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
        setSupportActionBar(binding.includeToolbar.toolbar)
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
        if (binding.includeBottomSheet.whereToEd.text?.isNotEmpty() == true) {
            binding.includeBottomSheet.whereToEd.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_remove,
                0
            )
        } else {
            binding.includeBottomSheet.whereToEd.setCompoundDrawablesWithIntrinsicBounds(
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
            binding.includeBottomSheet.whereToEd.editableText.clear()
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
        homeViewModel.isEnglishCheckboxChecked.observe(this, {
            englishCheckbox.isChecked = true
        })

        homeViewModel.isArabicCheckboxChecked.observe(this, {
            arabicCheckbox.isChecked = true
        })
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
        homeViewModel.isBlueCheckboxChecked.observe(this, {
            blueCheckbox.isChecked = true
            binding.checkInButton.setImageResource(R.drawable.button_icon_blue)
            binding.includeToolbar.toolbarLogo.setImageResource(R.drawable.sawa_logo_blue)
        })

        homeViewModel.isPinkCheckboxChecked.observe(this, {
            pinkCheckbox.isChecked = true
            binding.checkInButton.setImageResource(R.drawable.button_icon_pink)
            binding.includeToolbar.toolbarLogo.setImageResource(R.drawable.sawa_logo_pink)
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.english_checkbox -> {
                sharedPreference.saveLanguagesCheckBoxState(
                    applicationContext, Constants.ENG_CHECKBOX_CHECKED
                )
                sharedPreference.setAppLanguage(applicationContext, Constants.ENGLISH_LANGUAGE_LOCALE)
                recreateActivity()
            }

            R.id.arabic_checkbox -> {
                sharedPreference.saveLanguagesCheckBoxState(

                    applicationContext, Constants.ARAB_CHECKBOX_CHECKED
                )
                sharedPreference.setAppLanguage(applicationContext, Constants.ARABIC_LANGUAGE_LOCALE)
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
        }
    }
}
