package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.adapter.Adapter
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentCottonExperienceBinding
import com.smartirrigation.tuproq_namligi_monitoringg.model.CottonExperience

class CottonExperienceFragment: Fragment(R.layout.fragment_cotton_experience) {

    private lateinit var binding: FragmentCottonExperienceBinding
    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCottonExperienceBinding.bind(view)

        initViews()
    }

    private fun initViews() {

        adapter = Adapter {
            findNavController().navigate(it)
        }

        adapter.submitList(allPosts())
        binding.recyclerView.adapter = adapter
    }

    private fun allPosts(): ArrayList<CottonExperience> {
        val experienceList = ArrayList<CottonExperience>()
        experienceList.add(CottonExperience(0, requireActivity().getString(R.string.str_external_characters), R.id.action_cottonExperienceFragment_to_externalCharactersFragment, R.drawable.iv_external_0))
        experienceList.add(CottonExperience(1, requireActivity().getString(R.string.str_thermostat_scales), R.id.action_cottonExperienceFragment_to_thermostatScalesFragment, R.drawable.iv_thermostat_0))
        experienceList.add(CottonExperience(2, requireActivity().getString(R.string.str_data_snap), R.id.action_cottonExperienceFragment_to_dataSnapEquipmentFragment, R.drawable.iv_data_snap_0))
        experienceList.add(CottonExperience(2, requireActivity().getString(R.string.str_tdr), R.id.action_cottonExperienceFragment_to_TDRFragment, R.drawable.tdr))
        experienceList.add(CottonExperience(3, requireActivity().getString(R.string.str_ternetic_instrument), R.id.action_cottonExperienceFragment_to_terneticInstrumentFragment, R.drawable.iv_ternetic_0))
        experienceList.add(CottonExperience(4, requireActivity().getString(R.string.str_wdf), R.id.action_cottonExperienceFragment_to_WFDFragment, R.drawable.iv_wfd))
        experienceList.add(CottonExperience(5, requireActivity().getString(R.string.str_electronic), R.id.action_cottonExperienceFragment_to_electronicHandRefractometerFragment, R.drawable.iv_electronic))

        return experienceList
    }
}