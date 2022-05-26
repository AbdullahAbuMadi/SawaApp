package com.abumadi.sawaapp.ui.scanner

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abumadi.sawaapp.data.source.CheckedInInfo
import com.abumadi.sawaapp.R
import com.abumadi.sawaapp.databinding.ActivityScannerBinding
import com.abumadi.sawaapp.others.Constants
import com.abumadi.sawaapp.ui.base.BaseActivity
import com.abumadi.sawaapp.ui.home.HomeActivity
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private const val CAMERA_REQUEST_CODE = 101

class ScannerActivity : BaseActivity() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: ActivityScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPermissions()
        codeScanner()
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, binding.scannerQrCode)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
            formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
            // ex. listOf(BarcodeFormat.QR_CODE)
            autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
            scanMode = ScanMode.SINGLE// or CONTINUOUS or PREVIEW
            isAutoFocusEnabled = true // Whether to enable auto focus or not
            isFlashEnabled = false // Whether to enable flash or not

            //Callbacks
            decodeCallback = DecodeCallback {
                runOnUiThread {
                    val builder = MaterialAlertDialogBuilder(
                        this@ScannerActivity,
                        R.style.MyThemeOverlayAlertDialog
                    )
                    //set message for alert dialog
                    builder.setMessage(getString(R.string.dialog_message))
                    if (it.text == "Starbucks Amman Branch") {
                        builder.setTitle("${it.text}!")
                        placeIcon = R.drawable.starbucks_icon
                        placeName = "Starbucks"
                        branchName = "Starbucks Amman"
                        builder.setIcon(placeIcon ?: 0)

                    } else {
                        builder.setTitle("${it.text}!")
                        placeIcon = R.drawable.carefour_icon
                        placeName = "Carrefour"
                        branchName = "Carrefour CityMall"
                        builder.setIcon(placeIcon ?: 0)
                    }

                    //performing positive action
                    builder.setPositiveButton(getString(R.string.yes_dilog_click)) { _, _ ->
                        checkedInInfo = CheckedInInfo(
                            placeName ?: "",
                            placeIcon ?: 0,
                            branchName ?: "",
                            "0.0",
                            "00"
                        )
                        val intent = Intent(this@ScannerActivity, HomeActivity::class.java)
                        intent.putExtra("checkedInInfo", checkedInInfo)
                        startActivity(intent)
                        sharedPreference.setAppCurrentUi(
                            applicationContext,
                            Constants.CHECK_IN_LAYOUT_UI
                        )
                        sharedPreference.saveCheckedInInfo(applicationContext, checkedInInfo)
                        finish()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Log.e("Scanner", "Camera initialization error : ${it.message}")
            }
        }

        binding.scannerQrCode.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun setUpPermissions() {
        val permissions: Int = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )

        if (permissions != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You need the camera permission ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}