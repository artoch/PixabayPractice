package com.atsapp.pixabaytestapp.ui.acitvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.databinding.ActivityMainBinding
import com.atsapp.pixabaytestapp.ui.dialog.PixExitDialog
import com.atsapp.pixabaytestapp.ui.view_models.MainActivityViewModel
import com.atsapp.pixabaytestapp.utils.getNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: MainActivityViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding : ActivityMainBinding
    private lateinit var dialog : PixExitDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.clearFilter()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            navController = getNavController(binding.navHostFragmentContainer.id)
        }
    }

    override fun onBackPressed() {
        if (::dialog.isInitialized){
            if (dialog.isVisible){
                return
            }else{
                dialog.show(supportFragmentManager, "SHOW")
            }
        }else{
            dialog = PixExitDialog()
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, "SHOW")
        }
    }
}