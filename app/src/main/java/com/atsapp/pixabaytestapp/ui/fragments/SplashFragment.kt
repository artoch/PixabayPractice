package com.atsapp.pixabaytestapp.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.databinding.FragmentSplashBinding

/**
 * @author Alfredo Tochon
 * View del splash
 */
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        val splashTime:Long = 3000
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToPixMainFragment()
            view.findNavController().navigate(action)
        }, splashTime)
    }

}