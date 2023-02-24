package com.smartirrigation.tuproq_namligi_monitoringg.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val BLE_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val ANDROID_12_BLE_PERMISSIONS = arrayOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.ACCESS_FINE_LOCATION
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)


        requestBlePermissions(this, 1001)

        grantUriPermission()
        checkLocationIsEnabledOrNot()
    }

    fun requestBlePermissions(activity: Activity?, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) ActivityCompat.requestPermissions(
            activity!!,
            ANDROID_12_BLE_PERMISSIONS,
            requestCode
        ) else ActivityCompat.requestPermissions(
            activity!!, BLE_PERMISSIONS, requestCode
        )
    }


    private fun checkLocationIsEnabledOrNot() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        var networkEnabled = false

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }catch (e: Exception){
            e.printStackTrace()
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }catch (e: Exception){
            e.printStackTrace()
        }

        if (!gpsEnabled && !networkEnabled){
            AlertDialog.Builder(this)
                .setMessage("Enable gpd Servise")
                .setCancelable(false)
                .setPositiveButton("Enable", DialogInterface.OnClickListener { dialogInterface, i ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }).setPositiveButton("Cancel", null)
                .show()
        }
    }

    private fun grantUriPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,  Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
    }

}