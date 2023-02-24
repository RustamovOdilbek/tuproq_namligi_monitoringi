package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentAppAboutBinding


class AppAboutFragment : Fragment(R.layout.fragment_app_about) {

    private lateinit var binding: FragmentAppAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAppAboutBinding.bind(view)

        initViews()
    }

    private fun initViews() {

        binding.tvPhone1.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+998977169938")
            startActivity(intent)
        }

        binding.tvPhone2.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+998977658902")
            startActivity(intent)
        }

        binding.tvEmail1.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:mirzoolim89@mail.ru")
            startActivity(Intent.createChooser(emailIntent, "Send feedback"))
        }

        binding.tvEmail2.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:normat8689@gmail.com")
            startActivity(Intent.createChooser(emailIntent, "Send feedback"))
        }

        binding.tvUser4.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+998917751779")
            startActivity(intent)
        }

        binding.tvDeveloper.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+998917751779")
            startActivity(intent)
        }
    }

}