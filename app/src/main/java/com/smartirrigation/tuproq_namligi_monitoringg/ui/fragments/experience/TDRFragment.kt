package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentTdrBinding

class TDRFragment : Fragment(R.layout.fragment_tdr) {

    private lateinit var binding: FragmentTdrBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTdrBinding.bind(view)

        initViews()
    }

    private fun initViews() {

    }

}