package com.atsapp.pixabaytestapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.adapter.PictureAdapter
import com.atsapp.pixabaytestapp.aux_interface.utils.AdapterOnClick
import com.atsapp.pixabaytestapp.aux_interface.view_.SetupView
import com.atsapp.pixabaytestapp.data.models.Hit
import com.atsapp.pixabaytestapp.databinding.FragmentPixMainBinding
import com.atsapp.pixabaytestapp.ui.dialog.PixSearchDialogFragment
import com.atsapp.pixabaytestapp.ui.view_models.MainActivityViewModel
import com.atsapp.pixabaytestapp.utils.NetworkState.Companion.LOADED
import com.atsapp.pixabaytestapp.utils.NetworkState.Companion.LOADING
import com.atsapp.pixabaytestapp.utils.Status
import com.atsapp.pixabaytestapp.utils.gridLayout

/**
 * @author Alfredo Tochon
 * Pantalla principal donde muestra la data
 */
class PixMainFragment(override val TAG: String = "PixMainFragment") : Fragment(), SetupView, View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterOnClick<Hit> {

    private lateinit var binding: FragmentPixMainBinding
    private lateinit var adapter: PictureAdapter
    private lateinit var dialog: PixSearchDialogFragment
    private val toSpinner = arrayListOf("science", "education", "people", "feelings", "computer", "buildings")

    private val vm by lazy {
        ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pix_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPixMainBinding.bind(view)
        setupView()
    }

    override fun setupBindings() {
        val arrayAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            toSpinner
        )


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.apply {
            spnItemQuestionNew.adapter = arrayAdapter
            spnItemQuestionNew.onItemSelectedListener = this@PixMainFragment
            rvPixImages.layoutManager = gridLayout(requireContext())
            fbtnSearch.setOnClickListener(this@PixMainFragment)
            btnFilter.setOnClickListener(this@PixMainFragment)

        }

    }

    override fun setupObservers() {
        vm.myPictures.observe(requireActivity(), Observer {
            adapter = PictureAdapter(items = it.hits, adapterOnClick = this)
            binding.apply {
                rvPixImages.adapter = adapter
            }
        })

        vm.networkState.observe(requireActivity(), Observer {
            binding.apply {
                when (it){
                    LOADING -> {
                        pgBar.visibility = View.VISIBLE
                    }
                    else -> {
                        pgBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    //Inicia el dialogo para la busqueda
    private fun initSearchDialog(){
        if (::dialog.isInitialized){
            if (dialog.isVisible){
                return
            }else{
                dialog.show(childFragmentManager, "SHOW")
            }
        }else{
            startDialog()
        }
    }

    private fun startDialog(){
        dialog = PixSearchDialogFragment()
        dialog.show(childFragmentManager, "SHOW")
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0!!.id){
                fbtnSearch.id -> {
                    //Abre el dialogo por el boton
                    initSearchDialog()
                }
                btnFilter.id -> {
                    spnItemQuestionNew.performClick()
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        vm.getAllPicture(category = toSpinner[p2])
    }

    override fun adapterOnClick(item: Hit, pos: Int) {
        val action = PixMainFragmentDirections.actionPixMainFragmentToDetailPictureFragment(item)
        view?.findNavController()?.navigate(action)
    }

}