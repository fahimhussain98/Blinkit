package com.hussain.BlinkIt.Utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hussain.newkoltinproject.databinding.ProgressDialogBinding

object Utils {
    private var dialog : AlertDialog? = null

    //_____________________________here is the methode for the calling_________________________________________

    fun showDialog(context: Context, message: String){
        val progress  = ProgressDialogBinding.inflate(LayoutInflater.from(context))
        progress.tvMessage.text = message
        dialog = AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()
    }


    fun hideDialog(){
        dialog?.dismiss()
    }


    fun showToast(context: Context, message: String ){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    //to get the instance of user
    private var firebaseAuthInstance: FirebaseAuth? = null
    fun getAUthInstance(): FirebaseAuth {
        if (firebaseAuthInstance == null) {
            firebaseAuthInstance = FirebaseAuth.getInstance()
        }
        return firebaseAuthInstance!!
    }

    fun getCurrentUserId(): String? {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser?.uid
    }


}