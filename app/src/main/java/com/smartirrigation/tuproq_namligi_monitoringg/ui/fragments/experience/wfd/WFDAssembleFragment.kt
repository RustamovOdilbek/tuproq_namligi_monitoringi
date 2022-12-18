package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience.wfd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentWfdAssembleBinding

class WFDAssembleFragment : Fragment(R.layout.fragment_wfd_assemble) {

    private lateinit var binding: FragmentWfdAssembleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWfdAssembleBinding.bind(view)
    }

}