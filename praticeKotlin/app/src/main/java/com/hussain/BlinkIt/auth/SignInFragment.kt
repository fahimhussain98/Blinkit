package com.hussain.BlinkIt.auth

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.hussain.BlinkIt.Utils.Utils
import com.hussain.newkoltinproject.R
import com.hussain.newkoltinproject.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var bindingF : FragmentSignInBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//      bindingF = FragmentSignInBinding.inflate(layoutInflater)
        bindingF = FragmentSignInBinding.inflate(inflater, container, false)
        setStatusBarColor()
        getUserNumber()
        onContinueButtononClick()


        //return inflater.inflate(R.layout.fragment_sign_in, container, false)
        return bindingF.root
    }




    companion object {}
    //__________________HERE IS THE METHODE_______________________________________________________

    private fun setStatusBarColor(){
        activity?.window?.apply{
            val statusBarColors = ContextCompat.getColor(requireContext(),R.color.yellow)
            statusBarColor = statusBarColors
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
    private fun getUserNumber() {
        bindingF.etUserNumber.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(number: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val len = number?.length
                if (len == 10) {
                    bindingF.btnContinue.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                } else {
                    bindingF.btnContinue.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grayish_blue
                        )
                    )

                }
            }


            override fun afterTextChanged(p0: Editable?) {
            }
        })

    }
    private fun onContinueButtononClick() {
        bindingF.btnContinue.setOnClickListener{
            val number = bindingF.etUserNumber.text.toString()

            if(number.isEmpty() || number.length != 10){
                Utils.showToast(requireContext(), "Please Enter correct phone number")


            }else{
//                val bundle = Bundle()
//                bundle.putString("number",number)
//                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment);

                val bundle = Bundle()
                bundle.putString("number", number)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment, bundle)


            }

        }

    }

}