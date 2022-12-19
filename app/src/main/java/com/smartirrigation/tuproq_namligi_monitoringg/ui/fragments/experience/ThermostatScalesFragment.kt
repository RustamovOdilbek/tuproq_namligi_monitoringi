package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentExternalCharactersBinding
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentThermostatScalesBinding

class ThermostatScalesFragment : Fragment(R.layout.fragment_thermostat_scales) {

    private lateinit var binding: FragmentThermostatScalesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThermostatScalesBinding.bind(view)
    }

}