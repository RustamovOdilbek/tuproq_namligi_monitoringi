package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.location.*
import android.os.Bundle
import android.os.IBinder
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentControlBinding
import com.smartirrigation.tuproq_namligi_monitoringg.helper.bluetooth.SerialListener
import com.smartirrigation.tuproq_namligi_monitoringg.helper.bluetooth.SerialService
import com.smartirrigation.tuproq_namligi_monitoringg.helper.bluetooth.SerialSocket
import com.smartirrigation.tuproq_namligi_monitoringg.helper.bluetooth.TextUtil
import com.smartirrigation.tuproq_namligi_monitoringg.utils.Constants
import java.io.IOException
import java.util.*

class ControlFragment : BaseFragment(R.layout.fragment_control), ServiceConnection, SerialListener,
    LocationListener {
    private val TAG = "ControlFragment"
    private lateinit var binding: FragmentControlBinding
    lateinit var locationManager: LocationManager

    private enum class Connected {
        False, Pending, True
    }

    private var deviceAddress: String? = null
    private var service: SerialService? = null

    private var connected = Connected.False
    private var initialStart = true
    private var hexEnabled = false
    private var pendingNewline = false
    private var newline: String = TextUtil.newline_crlf

    /*
     * Lifecycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        deviceAddress = requireArguments().getString(Constants.MAC_NUMBER)
    }

    override fun onDestroy() {
        if (connected != Connected.False) disconnect()
        requireActivity().stopService(Intent(activity, SerialService::class.java))
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        if (service != null) service!!.attach(this) else requireActivity().startService(
            Intent(
                activity,
                SerialService::class.java
            )
        ) // prevents service destroy on unbind from recreated activity caused by orientation change
    }

    override fun onStop() {
        if (service != null && !requireActivity().isChangingConfigurations) service!!.detach()
        super.onStop()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        requireActivity().bindService(
            Intent(getActivity(), SerialService::class.java),
            this,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onDetach() {
        try {
            requireActivity().unbindService(this)
        } catch (ignored: Exception) {
        }
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        if (initialStart && service != null) {
            initialStart = false
            requireActivity().runOnUiThread { connect() }
        }
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder) {
        service = (binder as SerialService.SerialBinder).service
        service!!.attach(this)
        if (initialStart && isResumed) {
            initialStart = false
            requireActivity().runOnUiThread { connect() }
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        service = null
    }

    /*
     * UI
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentControlBinding.bind(view)

        initViews()
    }

    private fun initViews() {

    }

    /*
     * Serial + UI
     */
    private fun connect() {
        try {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val device = bluetoothAdapter.getRemoteDevice(deviceAddress)
            status("connecting...")
            connected = Connected.Pending
            val socket = SerialSocket(requireActivity().applicationContext, device)
            service!!.connect(socket)
        } catch (e: Exception) {
            onSerialConnectError(e)
        }
    }

    private fun disconnect() {
        connected = Connected.False
        service!!.disconnect()
    }

    private fun receive(datas: ArrayDeque<ByteArray>) {
        val spn = SpannableStringBuilder()
        for (data in datas) {
            if (hexEnabled) {
                spn.append(TextUtil.toHexString(data)).append('\n')
            } else {
                var msg = String(data)
                if (newline == TextUtil.newline_crlf && msg.length > 0) {
                    // don't show CR as ^M if directly before LF
                    msg = msg.replace(TextUtil.newline_crlf, TextUtil.newline_lf)
                    // special handling if CR and LF come in separate fragments
                    if (pendingNewline && msg[0] == '\n') {
                        if (spn.length >= 2) {
                            spn.delete(spn.length - 2, spn.length)
                        }
                    }
                    pendingNewline = msg[msg.length - 1] == '\r'
                }
                spn.append(TextUtil.toCaretString(msg, newline.length != 0))
            }
        }

        Log.d(TAG, "receive: ${spn.toString()}")
        binding.tvHumidityName.text = spn.toString()
    }

    private fun status(msg: String) {
        Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).show()
    }

    /*
     * SerialListener
     */
    override fun onSerialConnect() {
        status("connected")
        connected = Connected.True
    }

    override fun onSerialConnectError(e: Exception?) {
        status("connection failed: " + e!!.message)
        disconnect()
    }

    override fun onSerialRead(data: ByteArray?) {
        val datas = ArrayDeque<ByteArray>()
        datas.add(data)
        receive(datas)
    }

    override fun onSerialRead(datas: ArrayDeque<ByteArray?>?) {
        receive(datas as ArrayDeque<ByteArray>)
    }
    override fun onSerialIoError(e: Exception?) {
        status("connection lost: " + e!!.message)
        disconnect()
    }

    fun getLocation(){
        try {
            locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5.0f, this )
        }catch (e: SecurityException){
            e.printStackTrace()
        }
    }
    override fun onLocationChanged(location: Location) {
        Log.d("2222", "123333333654789")
        try {
            val geocoder = Geocoder(requireContext().applicationContext, Locale.ENGLISH)
            val address = geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>

            val country = address.get(0).countryName
            if (country.isNotEmpty() && country != null){
                binding.llCountry.visibility = View.VISIBLE
                binding.tvCountryName.text = country
            }

            val state = address.get(0).adminArea
            if (state.isNotEmpty() && state != null){
                binding.llState.visibility = View.VISIBLE
                binding.tvStateName.text = state
            }

            val addressline = address.get(0).getAddressLine(0)
            if (addressline.isNotEmpty() && addressline != null){
                binding.llAddress.visibility = View.VISIBLE
                binding.tvAddressName.text =  addressline
            }

        }catch (e: IOException){
            e.printStackTrace()
        }

    }
}