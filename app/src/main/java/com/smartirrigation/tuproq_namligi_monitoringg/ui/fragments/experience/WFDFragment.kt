package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.adapter.Adapter
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentWfdBinding
import com.smartirrigation.tuproq_namligi_monitoringg.model.CottonExperience

class WFDFragment : Fragment(R.layout.fragment_wfd) {

    private lateinit var binding: FragmentWfdBinding
    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWfdBinding.bind(view)

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
        experienceList.add(CottonExperience(0, requireActivity().getString(R.string.str_wfd_tasks), R.id.action_WFDFragment_to_WFDTaskFragment, R.drawable.iv_wfd_task))
        experienceList.add(CottonExperience(1, requireActivity().getString(R.string.str_wfd_component), R.id.action_WFDFragment_to_WFDComponentFragment, R.drawable.iv_wfd_component))
        experienceList.add(CottonExperience(2, requireActivity().getString(R.string.str_wfd_assemble), R.id.action_WFDFragment_to_WFDAssembleFragment, R.drawable.iv_wfd_assemble))
        experienceList.add(CottonExperience(3, requireActivity().getString(R.string.str_wfd_install), R.id.action_WFDFragment_to_WFDInstallFragment, R.drawable.iv_wfd_install))
        experienceList.add(CottonExperience(4, requireActivity().getString(R.string.str_wfd_work), R.id.action_WFDFragment_to_WFDWorksOrderFragment, R.drawable.iv_wfd_work_order))
        experienceList.add(CottonExperience(5, requireActivity().getString(R.string.str_wfd_comp), R.id.action_WFDFragment_to_WFDCompyuterWorksFragment, R.drawable.iv_wfd_comp))

        return experienceList
    }

}