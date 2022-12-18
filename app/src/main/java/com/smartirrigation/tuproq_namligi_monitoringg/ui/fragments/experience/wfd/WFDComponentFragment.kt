package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience.wfd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentWfdBinding
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentWfdComponentBinding

class WFDComponentFragment : Fragment(R.layout.fragment_wfd_component) {

    private lateinit var binding: FragmentWfdComponentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWfdComponentBinding.bind(view)
    }

}