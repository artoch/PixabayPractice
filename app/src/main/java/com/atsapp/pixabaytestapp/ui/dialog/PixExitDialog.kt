package com.atsapp.pixabaytestapp.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.aux_interface.view_.SetupView
import com.atsapp.pixabaytestapp.databinding.DialogExitBinding

class PixExitDialog(override val TAG: String = "PixExitDialog"): DialogFragment(), SetupView,View.OnClickListener {


    lateinit var binding: DialogExitBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_exit, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogExitBinding.bind(view)
        setupView()
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.attributes?.windowAnimations = R.style.checkDialogAnimation
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.55).toInt()
        dialog!!.window?.setLayout(width, height)
    }


    override fun setupBindings() {
        binding.apply {
            cancelButton.setOnClickListener(this@PixExitDialog)
            aceptButton.setOnClickListener(this@PixExitDialog)
        }
    }

    override fun setupObservers() {

    }

    override fun onClick(p0: View?) {
        binding.apply {
            when(p0!!.id){
                aceptButton.id -> {
                    requireActivity().finish()
                }
                cancelButton.id -> {
                    dismiss()
                }
            }
        }
    }
}