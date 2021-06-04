package com.atsapp.pixabaytestapp.ui.acitvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.databinding.ActivityMainBinding
import com.atsapp.pixabaytestapp.ui.view_models.MainActivityViewModel
import com.atsapp.pixabaytestapp.utils.getNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: MainActivityViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            navController = getNavController(binding.navHostFragmentContainer.id)
        }
    }
}