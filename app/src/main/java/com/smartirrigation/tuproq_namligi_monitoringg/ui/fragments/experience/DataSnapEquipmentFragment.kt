package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentDataSnapEquipmentBinding

//Data snap uskunasi

class DataSnapEquipmentFragment : Fragment(R.layout.fragment_data_snap_equipment) {

    private lateinit var binding: FragmentDataSnapEquipmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDataSnapEquipmentBinding.bind(view)
    }

}