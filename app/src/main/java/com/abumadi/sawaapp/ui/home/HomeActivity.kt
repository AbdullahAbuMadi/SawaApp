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
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.data.constantsclasses.Destinations
import com.abumadi.sawaapp.data.constantsclasses.Places
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.scanner.ScannerActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.languages_checkboxes.view.*
import kotlinx.android.synthetic.main.themes_checkboxes.view.*

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, TextWatcher,
    View.OnTouchListener, View.OnClickListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var blueCheckbox: CompoundButton
    private lateinit var pinkCheckbox: CompoundButton
    private lateinit var arabicCheckbox: CompoundButton
    private lateinit var englishCheckbox: CompoundButton
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModelSetUp()
        navDrawerSetUp()
        toolbarSetUp()
        navViewHeaderClick()
        destinationRecyclerViewSetUp()
        placesRecyclerViewSetUp()
        languageCheckboxesInflate()
        themesCheckboxesInflate()
        mNavigationView.setNavigationItemSelectedListener(this)
        where_to_ed.addTextChangedListener(this)
        check_in_button.setOnClickListener(this)
    }

    private fun homeViewModelSetUp() {
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun navDrawerSetUp() {
        mDrawerLayout = findViewById(R.id.drawer)
        mDrawerLayout.setScrimColor(resources.getColor(android.R.color.transparent))//no shadow
        mNavigationView = findViewById(R.id.nav_view)
    }

    private fun placesRecyclerViewSetUp() {
        places_rv.adapter = PlacesAdapter(this, Places.getPlacesList())
    }

    private fun destinationRecyclerViewSetUp() {
        destination_rv.adapter = DestinationsAdapter(this, Destinations.getDestinationsList(this))
        //to solve two recyclerview in same bottomSheet problem
        androidx.core.view.ViewCompat.setNestedScrollingEnabled(destination_rv, false)
    }

    private fun navViewHeaderClick() {
        val headerView = mNavigationView.getHeaderView(0)
        val headerBackButton = headerView.findViewById<ImageButton>(R.id.iv_back_button_header)
        headerBackButton.setOnClickListener {
            mDrawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    private fun toolbarSetUp() {
        setSupportActionBar(toolbar)
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

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(viewText: Editable?) {
        if (viewText?.toString()?.isEmpty() == true) {
            where_to_ed.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_search),
                null
            )

        } else {
            where_to_ed.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_remove),
                null
            )
            where_to_ed.setOnTouchListener(this)
        }
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {//if click on remove
            where_to_ed.editableText.clear()
        }
        return true
    }

    private fun languageCheckboxesInflate() {
        val languages = mNavigationView.menu.findItem(R.id.languagesCheckBoxes)
        englishCheckbox = MenuItemCompat.getActionView(languages).english_checkbox as CompoundButton
        arabicCheckbox = MenuItemCompat.getActionView(languages).arabic_checkbox as CompoundButton
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
        val themes = mNavigationView.menu.findItem(R.id.themesCheckBoxes)
        blueCheckbox = MenuItemCompat.getActionView(themes).blue_checkbox as CompoundButton
        pinkCheckbox = MenuItemCompat.getActionView(themes).pink_checkbox as CompoundButton
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
            check_in_button.setImageResource(R.drawable.button_icon_blue)
            toolbar_logo.setImageResource(R.drawable.sawa_logo_blue)
        })

        homeViewModel.isPinkCheckboxChecked.observe(this, {
            pinkCheckbox.isChecked = true
            check_in_button.setImageResource(R.drawable.button_icon_pink)
            toolbar_logo.setImageResource(R.drawable.sawa_logo_pink)
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.english_checkbox -> {
                db.saveLanguagesChickBoxState(
                    applicationContext, Constants.ENG_CHECKBOX_CHECKED
                )
                db.setAppLanguage(applicationContext, Constants.ENGLISH_LANGUAGE_LOCALE)
                recreateActivity()
            }

            R.id.arabic_checkbox -> {
                db.saveLanguagesChickBoxState(

                    applicationContext, Constants.ARAB_CHECKBOX_CHECKED
                )
                db.setAppLanguage(applicationContext, Constants.ARABIC_LANGUAGE_LOCALE)
                recreateActivity()
            }

            R.id.blue_checkbox -> {
                db.saveThemesChickBoxState(

                    applicationContext, Constants.BLUE_CHECKBOX_CHECKED
                )
                db.setAppTheme(applicationContext, Constants.THEME_BLUE)
                recreateActivity()

            }
            R.id.pink_checkbox -> {
                db.saveThemesChickBoxState(

                    applicationContext, Constants.PINK_CHECKBOX_CHECKED
                )
                db.setAppTheme(applicationContext, Constants.THEME_PINK)
                recreateActivity()
            }
            R.id.check_in_button -> {
                startActivity(Intent(this, ScannerActivity::class.java))
            }
        }
    }
}
