package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.adapter.Adapter
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentElectronicHandRefractometerBinding
import com.smartirrigation.tuproq_namligi_monitoringg.model.CottonExperience

//Elektron qo'l refraktometr

class ElectronicHandRefractometerFragment : Fragment(R.layout.fragment_electronic_hand_refractometer) {

    private lateinit var binding: FragmentElectronicHandRefractometerBinding
    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentElectronicHandRefractometerBinding.bind(view)

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
        experienceList.add(CottonExperience(0, requireActivity().getString(R.string.str_electronic_task), R.id.action_electronicHandRefractometerFragment_to_tasksFragment, R.drawable.iv_electroniv_hand_1))
        experienceList.add(CottonExperience(1, requireActivity().getString(R.string.str_advantages), R.id.action_electronicHandRefractometerFragment_to_advantagesFragment, R.drawable.iv_electronic_hand_2))
        experienceList.add(CottonExperience(2, requireActivity().getString(R.string.str_colibration), R.id.action_electronicHandRefractometerFragment_to_calibrationFragment, R.drawable.iv_electronic_hand_3))
        experienceList.add(CottonExperience(3, requireActivity().getString(R.string.str_rule_work), R.id.action_electronicHandRefractometerFragment_to_rulesWorkFragment, R.drawable.iv_electronic_hand_4))
        experienceList.add(CottonExperience(4, requireActivity().getString(R.string.str_concentration), R.id.action_electronicHandRefractometerFragment_to_determineConcentrationFragment, R.drawable.iv_electronic_hand_5))
        experienceList.add(CottonExperience(5, requireActivity().getString(R.string.str_refractometer), R.id.action_electronicHandRefractometerFragment_to_refractometerFragment, R.drawable.iv_electronic_hand_6))

        return experienceList
    }
}

