package com.atsapp.pixabaytestapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import com.atsapp.pixabaytestapp.BaseApp
import com.atsapp.pixabaytestapp.BaseApp.Companion.sessionManager
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.aux_interface.view_.SetupView
import com.atsapp.pixabaytestapp.databinding.DialogFragmentPixSearchBinding
import com.atsapp.pixabaytestapp.ui.view_models.MainActivityViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * @author Alfredo Tochon
 * Clase para mostrar el dialogo del input text
 */
class PixSearchDialogFragment(override val TAG: String = "") : BottomSheetDialogFragment(), SetupView, View.OnClickListener {

    private val vm by lazy {
        ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    lateinit var binding: DialogFragmentPixSearchBinding

    var query = ""
    var category = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_pix_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogFragmentPixSearchBinding.bind(view)
        setupView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun setupBindings() {
        binding.apply {
            btnSearch.setOnClickListener(this@PixSearchDialogFragment)
            tvSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    dismiss()
                    query = tvSearch.text.toString()
                    sessionManager.setQuery(query)
                    sessionManager.setCategory("")
                    vm.getAllPicture(query, lang = "es")
                    return@OnEditorActionListener true
                }
                false
            })
        }
    }



    override fun setupObservers() {

    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0!!.id){
                btnSearch.id -> {//Boton de busqueda
                    dismiss()
                    query = tvSearch.text.toString()
                    sessionManager.setQuery(query)
                    sessionManager.setCategory("")
                    vm.getAllPicture(query, lang = "es")
                }
            }
        }
    }





}