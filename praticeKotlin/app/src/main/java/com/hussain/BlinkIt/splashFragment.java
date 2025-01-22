package com.hussain.BlinkIt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hussain.newkoltinproject.R;


public class splashFragment extends Fragment {
   // private lateinit var binding : FragmentSplashBinding



    public splashFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_splash, container, false);
//        binding = FragmentSplashBinding.inflate(layoutInflater)
//        setStatusBarColor()
//        Handler(Looper.getMainLooper()).postDelayed({
//                findNavController().navigate(R.id.action_splash2Fragment_to_signInFragment)
//        },3000)
//        return binding.root

    }
}