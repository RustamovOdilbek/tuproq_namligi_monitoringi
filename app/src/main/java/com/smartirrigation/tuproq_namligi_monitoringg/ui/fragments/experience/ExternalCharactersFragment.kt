package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments.experience

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentExternalCharactersBinding

/*
G'ozani sug'orish muddatini tashqi belgilar yordamida aniqlash
 */

class ExternalCharactersFragment : Fragment(R.layout.fragment_external_characters) {

    private lateinit var binding: FragmentExternalCharactersBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExternalCharactersBinding.bind(view)

        initViews()
    }

    private fun initViews() {

    }

}