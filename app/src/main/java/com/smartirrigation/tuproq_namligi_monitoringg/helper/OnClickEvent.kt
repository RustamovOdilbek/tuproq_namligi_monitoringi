package com.smartirrigation.tuproq_namligi_monitoringg.helper

import com.smartirrigation.tuproq_namligi_monitoringg.model.BluetoothDeviceModel

interface OnClickEvent {
    fun setOnClickListener(bluetoothDeviceModel: BluetoothDeviceModel)
}