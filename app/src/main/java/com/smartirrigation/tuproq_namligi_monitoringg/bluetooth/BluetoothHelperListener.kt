package com.smartirrigation.tuproq_namligi_monitoringg.library

import android.bluetooth.BluetoothDevice

interface BluetoothHelperListener {

    fun onStartDiscovery()

    fun onFinishDiscovery()

    fun onEnabledBluetooth()

    fun onDisabledBluetooh()

    fun getBluetoothDeviceList(device: BluetoothDevice?)
}