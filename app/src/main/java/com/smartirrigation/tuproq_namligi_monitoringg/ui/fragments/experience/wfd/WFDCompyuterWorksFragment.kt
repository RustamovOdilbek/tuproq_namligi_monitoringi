package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience.wfd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentWfdCompyuterWorksBinding

class WFDCompyuterWorksFragment : Fragment(R.layout.fragment_wfd_compyuter_works) {

    private lateinit var binding: FragmentWfdCompyuterWorksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWfdCompyuterWorksBinding.bind(view)
    }
}