package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentControlBinding
import com.smartirrigation.tuproq_namligi_monitoringg.utils.Constants.MAC_NUMBER
import com.smartirrigation.tuproq_namligi_monitoringg.utils.Constants.NAME
import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream
import java.util.*


class ControlFragment : BaseFragment(R.layout.fragment_control) {

    private lateinit var binding: FragmentControlBinding
    private var name: String? = null
    private var macnumber: String? = null
    var myBluetooth: BluetoothAdapter? = null
    var btSocket: BluetoothSocket? = null
    private var isBtConnected = false
    private var progress: ProgressDialog? = null
    private val TAG = "ControlFragment"


    // This UUID is unique and fix id for this device
    val myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString(NAME)
        macnumber = arguments?.getString(MAC_NUMBER)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentControlBinding.bind(view)

        initViews()
    }

    private fun initViews() {

        ConnectBT().execute()

        receiveData(btSocket!!)
    }

    @Throws(IOException::class)
    fun receiveData(socket: BluetoothSocket) {
        val socketInputStream: InputStream = socket.getInputStream()
        val buffer = ByteArray(256)
        var bytes: Int

        while (true) {
            try {
                bytes = socketInputStream.read(buffer)
                val readMessage = String(buffer, 0, bytes)
                Log.i("logging", readMessage + "")
            } catch (e: IOException) {
                break
            }
        }
    }


    private fun sendSignal(number: String) {
        if (btSocket != null) {
            try {
                btSocket!!.outputStream.write(number.toByteArray())
            } catch (e: IOException) {
                msg("Error")
            }
        }
    }

    private fun Disconnect() {
        if (btSocket != null) {
            try {
                btSocket!!.close()
            } catch (e: IOException) {
                msg("Error")
            }
        }
        findNavController().popBackStack()
    }

    private fun msg(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    inner class ConnectBT : AsyncTask<Void?, Void?, Void?>() {
        private var ConnectSuccess = true
        override fun onPreExecute() {
            Log.d(TAG, "onPreExecute: 1")
            progress = ProgressDialog.show(requireContext(), "Connecting...", "Please Wait!!!")
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.")

            } else {
                msg("Connected")
                isBtConnected = true
            }
            progress!!.dismiss()
        }

        @SuppressLint("MissingPermission")
        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter()
                    val dispositivo: BluetoothDevice = myBluetooth!!.getRemoteDevice(macnumber)
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                    btSocket!!.connect()
                }
            } catch (e: IOException) {
                ConnectSuccess = false
            }
            return null
        }
    }
}