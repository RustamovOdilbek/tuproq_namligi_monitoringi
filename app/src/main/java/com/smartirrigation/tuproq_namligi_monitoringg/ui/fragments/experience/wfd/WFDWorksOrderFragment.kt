package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience.wfd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentWfdWorksOrderBinding

class WFDWorksOrderFragment : Fragment(R.layout.fragment_wfd_works_order) {

    private lateinit var binding: FragmentWfdWorksOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWfdWorksOrderBinding.bind(view)
    }
}

