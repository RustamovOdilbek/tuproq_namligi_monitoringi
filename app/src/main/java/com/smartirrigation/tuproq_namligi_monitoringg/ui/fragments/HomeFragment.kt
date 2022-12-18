package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smartirrigation.tuproq_namligi_monitoringg.R
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.FragmentHomeBinding
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        binding.clCottonExperience.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cottonExperienceFragment)
        }

        binding.clAppInformation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_appAboutFragment)
        }

        binding.clMositureMonitoring.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moistureMonitoringFragment)
        }

        binding.clWateringRecommendations.setOnClickListener {

            openPdf()
        }
    }

    fun openPdf(){
        // Open the PDF file from raw folder
        val inputStream = resources.openRawResource(R.raw.malumot)

        // Copy the file to the cache folder
        inputStream.use { inputStream ->
            val file = File(requireContext().cacheDir, "mypdf.pdf")
            FileOutputStream(file).use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (inputStream.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
        }

        val cacheFile = File(requireContext().cacheDir, "mypdf.pdf")

        var packageName = "com.smartirrigation.tuproq_namligi_monitoringg"
        // Get the URI of the cache file from the FileProvider
        val uri = FileProvider.getUriForFile(requireContext(), "$packageName.provider", cacheFile)
        if (uri != null) {
            // Create an intent to open the PDF in a third party app
            val pdfViewIntent = Intent(Intent.ACTION_VIEW)
            pdfViewIntent.data = uri
            pdfViewIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(pdfViewIntent, "Choos PDF viewer"))
        }
    }


    private fun CopyAssets() {
        val assetManager: AssetManager = requireActivity().getAssets()
        var inputStream: InputStream? = null
        var out: OutputStream? = null
        val file: File = File(requireContext().getFilesDir(), "raw/malumot_cotton.pdf")
        try {
            inputStream = assetManager.open("raw/malumot_cotton.pdf")
            out = requireActivity().openFileOutput(file.name, Context.MODE_WORLD_READABLE)
            copyFile(inputStream, out)
            inputStream.close()
            inputStream = null
            out!!.flush()
            out.close()
            out = null
        } catch (e: java.lang.Exception) {
            Log.e("tag", e.message!!)
        }
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(
            Uri.parse("file:/" + requireActivity().getFilesDir().toString() + "/raw/malumot_cotton.pdf"),
            "application/pdf"
        )
        startActivity(intent)
    }

    private fun copyFile(inputStream: InputStream, out: OutputStream?) {
        val buffer = ByteArray(1024)
        var read: Int
        while (inputStream.read(buffer).also { read = it } != -1) {
            out!!.write(buffer, 0, read)
        }
    }

}