package com.hussain.BlinkIt.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hussain.BlinkIt.Utils.Utils
import com.hussain.BlinkIt.activity.UsersMainActivity
import com.hussain.BlinkIt.models.Users
import com.hussain.BlinkIt.viewmodels.AuthViewModel
import com.hussain.newkoltinproject.R
import com.hussain.newkoltinproject.databinding.FragmentOTPBinding
import kotlinx.coroutines.launch


class OTPFragment : Fragment() {
    private val viewModel : AuthViewModel by viewModels()
    private lateinit var binding : FragmentOTPBinding
    private lateinit var userNumber: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOTPBinding.inflate(layoutInflater)
        getUserNumber()
        onLoginBtnClick()
        sendOTP()
        customizingEnteringOTP()
        onBackButtonClicked()


        // return inflater.inflate(R.layout.fragment_o_t_p, container, false)
        return binding.root



    }




//_________________________________here is the methode_____________________

    companion object {}
    private fun getUserNumber() {
        val bundle = arguments
        userNumber = bundle?.getString ("number").toString()

        binding.tvUserNumber.text = userNumber
    }

    private fun onLoginBtnClick() {
        binding.btnLogin.setOnClickListener {
            Utils.showDialog(requireContext(), "Signing you...")
            val editTexts = arrayOf(
                binding.etOtp1,
                binding.etOtp2,
                binding.etOtp3,
                binding.etOtp4,
                binding.etOtp5,
                binding.etOtp6
            )
            val otp = editTexts.joinToString("") { it.text.toString() }

            if (otp.length < editTexts.size) {
                Utils.showToast(requireContext(), "please enter right otp")
            } else {
                editTexts.forEach { it.text?.clear();it.clearFocus() }
                verifyOtp(otp)
            }
        }
    }

    private fun verifyOtp(otp: String) {
        val user = Users(uid = Utils.getCurrentUserId(),userPhoneNumber = userNumber,userAddress = "null")
      //  viewModel.signInWithPhoneAuthCredential(otp, userNumber, user)
        viewModel.signInWithPhoneAuthCredential(otp, userNumber,user)
        lifecycleScope.launch {
            viewModel.issignedSuccessfully.collect{
                if(it){
                    Utils.hideDialog()
                    Utils.showToast(requireContext(),"Logged In...")
                    startActivity(Intent(requireActivity(), UsersMainActivity::class.java))
                    requireActivity().finish()

                }
            }


        }

    }

    private fun sendOTP() {
        Utils.showDialog(requireContext(), "Sending OTP ...")
        viewModel.apply {
            viewModel.sendOTP(userNumber, requireActivity())
            lifecycleScope.launch {
                otpSent.collect {
                    if (it) { // it  = true // likh sakte hai
                        Utils.hideDialog()
                        Utils.showToast(requireContext(), "Otp sent...")
                    }
                }
            }
        }
    }

    private fun onBackButtonClicked() {
        binding.tbOtpFragment.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_signInFragment)
        }
    }

    private fun customizingEnteringOTP() {
        val editTexts = arrayOf(
            binding.etOtp1,
            binding.etOtp2,
            binding.etOtp3,
            binding.etOtp4,
            binding.etOtp5,
            binding.etOtp6
        )
        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        }
                    } else if (s?.length == 0) {
                        if (i > 0) {
                            editTexts[i - 1].requestFocus()
                        }
                    }

                }

            })
        }
    }

}