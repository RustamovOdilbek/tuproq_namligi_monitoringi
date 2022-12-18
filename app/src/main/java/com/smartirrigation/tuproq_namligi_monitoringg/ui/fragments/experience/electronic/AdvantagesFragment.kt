package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience.electronic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentAdvantagesBinding

class AdvantagesFragment : Fragment(R.layout.fragment_advantages) {

    private lateinit var binding: FragmentAdvantagesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdvantagesBinding.bind(view)
    }
}