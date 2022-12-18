package com.smartirrigation.tuproq_namligi_monitoringg.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartirrigation.tuproq_namligi_monitoringg.utils.ProgressBarDialog

open class BaseFragment(private val layoutResID: Int) : Fragment() {

    lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingDialog = ProgressBarDialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResID, container, false)
    }

    open fun showProgress() {
        loadingDialog.show()
        loadingDialog
    }

    open fun hideProgress() {
        loadingDialog.dismiss()
    }


}