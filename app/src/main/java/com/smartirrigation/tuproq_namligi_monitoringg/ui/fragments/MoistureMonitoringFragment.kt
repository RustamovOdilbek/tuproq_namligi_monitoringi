package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.adapter.BluetoothListAdapter
import com.smartirrigation.tuproq_namligi_monitoringg.bluetooth.BluetoothHelper
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentMoistureMonitoringBinding
import com.smartirrigation.tuproq_namligi_monitoringg.helper.OnClickEvent
import com.smartirrigation.tuproq_namligi_monitoringg.library.BluetoothHelperListener
import com.smartirrigation.tuproq_namligi_monitoringg.model.BluetoothDeviceModel
import com.smartirrigation.tuproq_namligi_monitoringg.utils.Constants.MAC_NUMBER
import com.smartirrigation.tuproq_namligi_monitoringg.utils.Constants.NAME
import kotlin.math.log


class MoistureMonitoringFragment : BaseFragment(R.layout.fragment_moisture_monitoring),
    BluetoothHelperListener {

    private lateinit var binding: FragmentMoistureMonitoringBinding
    private lateinit var bluetoothHelper: BluetoothHelper
    private lateinit var viewAdapter: BluetoothListAdapter
    private var itemList = ArrayList<BluetoothDeviceModel>()
    private var macAddress = HashSet<String>()
    private var sensorMac = HashSet<String>()
    private val TAG = "MoistureMonitoringFragm"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMoistureMonitoringBinding.bind(view)

        initViews()
    }

    fun initViews(){

        addSensorMac()

        bluetoothHelper = BluetoothHelper(requireContext(), this)
            .setPermissionRequired(true)
            .create()

        if (bluetoothHelper.isBluetoothEnabled()){
            binding.enableDisable.text = "Bluetooth State Off"
            binding.startStop.visibility = View.VISIBLE
        }
        else{
            binding.enableDisable.text = "Bluetooth State On"
            binding.startStop.visibility = View.GONE
        }

        if (bluetoothHelper.isBluetoothScanning()) binding.startStop.text = "Stop discovery"
        else binding.startStop.text = "Start discovery"

        binding.enableDisable.setOnClickListener {
            if (bluetoothHelper.isBluetoothEnabled()) {

                bluetoothHelper.disableBluetooth()

                binding.startStop.visibility = View.GONE

            } else {
                bluetoothHelper.enableBluetooth()

                binding.startStop.visibility = View.VISIBLE
            }
        }

        binding.startStop.setOnClickListener {
            if (bluetoothHelper.isBluetoothScanning()) {
                bluetoothHelper.stopDiscovery()
            } else {
                bluetoothHelper.startDiscovery()
                showProgress()
            }
        }

        viewAdapter = BluetoothListAdapter(object : OnClickEvent{
            override fun setOnClickListener(bluetoothDeviceModel: BluetoothDeviceModel) {
               findNavController().navigate(R.id.action_moistureMonitoringFragment_to_controlFragment,
                   bundleOf(NAME to bluetoothDeviceModel.name, MAC_NUMBER to bluetoothDeviceModel.macNumber))
            }

        })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }

    }

    private fun addSensorMac() {
        sensorMac.add("sensor №1  - 100 va 120")
        sensorMac.add("sensor №2  - 200 va 220")
        sensorMac.add("sensor №3  - 300 va 320")
        sensorMac.add("sensor №4  - 400 va 420")
        sensorMac.add("sensor №5  - 500 va 520")
        sensorMac.add("WFD  №1  - 150")
        sensorMac.add("WFD  №2  - 250")
        sensorMac.add("WFD №3  - 350")
        sensorMac.add("WFD №4  - 450")
        sensorMac.add("WFD №5  - 550")
    }

    override fun onStartDiscovery() {
        Log.d(TAG, "onStartDiscovery: 1")
        binding.startStop.text = "Stop discovery"
        itemList.clear()
        viewAdapter.submitList(itemList)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onFinishDiscovery() {
        Log.d(TAG, "onFinishDiscovery: 2")
        binding.startStop.text = "Start discovery"
        hideProgress()
    }

    override fun onEnabledBluetooth() {
        Log.d(TAG, "onEnabledBluetooth: 3")
        binding.enableDisable.text = "Bluetooth State Off"
    }

    override fun onDisabledBluetooh() {
        Log.d(TAG, "onDisabledBluetooh: 4")
        binding.enableDisable.text = "Bluetooth State On"

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun getBluetoothDeviceList(device: BluetoothDevice?) {

//        if (sensorMac.contains(device!!.address) && !macAddress.contains(device?.address)){
//
//        }

        Log.d(TAG, "getBluetoothDeviceList: 5")

        hideProgress()

        if (!macAddress.contains(device?.address)){
            macAddress.add(device!!.address)
            itemList.add(BluetoothDeviceModel(device?.name, device?.address))
            viewAdapter.submitList(itemList)
            viewAdapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        bluetoothHelper.registerBluetoothStateChanged()
    }

    override fun onStop() {
        super.onStop()
        bluetoothHelper.unregisterBluetoothStateChanged()
    }

}