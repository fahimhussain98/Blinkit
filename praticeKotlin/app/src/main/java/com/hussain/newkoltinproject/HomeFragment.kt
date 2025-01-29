package com.hussain.newkoltinproject

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.hussain.BlinkIt.Utils.Constants
import com.hussain.BlinkIt.adapter.AdapterCategory
import com.hussain.BlinkIt.models.Category
import com.hussain.newkoltinproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        setStatusBarColor()
        setAllCategories()



        return binding.root
    }
    //________here is the methode--------------------------
    private fun setStatusBarColor() {
        activity?.window?.apply {
            val statusBarColors = ContextCompat.getColor(requireContext(), R.color.diyaFlame)
            statusBarColor = statusBarColors
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
    private fun setAllCategories() {
        val categoriesList = ArrayList<Category>()


        for (i in 0 until Constants.allProductsCategory.size) {
            categoriesList.add(
                Category(
                    Constants.allProductsCategory[i],
                    Constants.allProductsCategoryIcon[i]
                )
            )
        }

         //binding.rvCategories.adapter = AdapterCategory(categoriesList,::onCategoryClicked)
        binding.rvCategories.adapter = AdapterCategory(categoriesList)

    }


}