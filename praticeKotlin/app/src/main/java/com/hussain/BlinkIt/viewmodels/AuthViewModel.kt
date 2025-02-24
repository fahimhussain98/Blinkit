package com.hussain.BlinkIt.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.hussain.BlinkIt.Utils.Utils
import com.hussain.BlinkIt.models.Users
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel(){



    private val _verificationId = MutableStateFlow<String?>(null)

    private val _otpSent = MutableStateFlow(false)
    val otpSent = _otpSent //jab otp mil jye tu usee pta ho mil gya otp mean true

    private val _issignedSuccessfully = MutableStateFlow(false)
    val issignedSuccessfully = _issignedSuccessfully


    private val _isACurrentUser = MutableStateFlow(false)
    val isACurrentUser = _isACurrentUser

    init {
        Utils.getAUthInstance().currentUser?.let {
            _isACurrentUser.value = true
        }
    }

    fun sendOTP(userNUmber: String, activity: Activity) {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {

                _verificationId.value = verificationId
                _otpSent.value = true

            }
        }
        val options = PhoneAuthOptions.newBuilder(Utils.getAUthInstance())
            .setPhoneNumber("+91$userNUmber") // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

//    fun signInWithPhoneAuthCredential(otp: String, userNumber: String, user: Users) {
//        val credential = PhoneAuthProvider.getCredential(_verificationId.value.toString(), otp)
//        FirebaseMessaging.getInstance().token.addOnCompleteListener {
//            user.userToken=it.result
//            Utils.getAUthInstance().signInWithCredential(credential).addOnCompleteListener { task ->
//                user.uid = Utils.getCurrentUserId()
//                if (task.isSuccessful) {
//                    FirebaseDatabase.getInstance().getReference("AllUsers").child("Users")
//                        .child(user.uid!!).setValue(user)
//                    issignedSuccessfully.value = true
//
//                }
//            }
//        }
//
//
//
//    }
//

//    fun signInWithPhoneAuthCredential(otp: String, userNumber: String, user: Users){
//        val credential   =  PhoneAuthProvider.getCredential(_verificationId.value.toString(),otp)
//        Utils.getAUthInstance().signInWithCredential(credential)
//            .addOnCompleteListener{task->
//                if(task.isSuccessful){
//                    FirebaseDatabase.getInstance().getReference("AllUsers")
//                        .child("Users")
//                        .child(user.uid!!).setValue(user)
//                    _issignedSuccessfully.value = true
//                }
//            }
//    }
        fun signInWithPhoneAuthCredential(otp: String, userNumber: String, user: Users) {
    val verificationId = _verificationId.value
    if (verificationId == null) {
        Log.e("AuthViewModel", "Verification ID is null")
        return
    }

    val credential = PhoneAuthProvider.getCredential(verificationId, otp)
    val auth = Utils.getAUthInstance()
    if (auth == null) {
        Log.e("AuthViewModel", "FirebaseAuth instance is null")
        return
    }

    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Get the FirebaseUser object
                val firebaseUser = auth.currentUser
                if (firebaseUser != null) {
                    // Set the UID from FirebaseUser to your user object
                    user.uid = firebaseUser.uid

                    // Write the user data to Firebase Realtime Database
                    FirebaseDatabase.getInstance().getReference("AllUsers")
                        .child("Users")
                        .child(user.uid!!).setValue(user)
                    _issignedSuccessfully.value = true
                } else {
                    Log.e("AuthViewModel", "FirebaseUser is null")
                }
            } else {
                Log.e("AuthViewModel", "Sign-in failed: ${task.exception?.message}")
            }
        }
}
    //______________________

}