package com.hussain.BlinkIt

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
//import com.hussain.BlinkitApp.databinding.FragmentSplash2Binding
import com.hussain.newkoltinproject.R
import com.hussain.newkoltinproject.databinding.FragmentSplash2Binding


class splash2Fragment : Fragment() {
    private lateinit var bindingF : FragmentSplash2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        bindingF = FragmentSplash2Binding.inflate(layoutInflater)
        setStatusBarColor()
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splash2Fragment_to_signInFragment)
        },3000)
        return bindingF.root

    }

    companion object {


    }
    //__________________HERE IS THE METHODE_______________________________________________________

    private fun setStatusBarColor(){
        activity?.window?.apply{
            val statusBarColors = ContextCompat.getColor(requireContext(), R.color.yellow)
            statusBarColor = statusBarColors
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        }
    }

}