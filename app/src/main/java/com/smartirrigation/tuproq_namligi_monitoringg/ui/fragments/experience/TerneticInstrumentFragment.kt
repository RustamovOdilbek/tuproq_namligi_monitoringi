package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentTerneticInstrumentBinding

//Tenziometr asbob

class TerneticInstrumentFragment : Fragment(R.layout.fragment_ternetic_instrument) {

    private lateinit var binding: FragmentTerneticInstrumentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTerneticInstrumentBinding.bind(view)
    }

}