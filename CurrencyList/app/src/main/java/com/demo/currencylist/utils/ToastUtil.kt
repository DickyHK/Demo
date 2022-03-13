package com.demo.currencylist.utils

import android.app.Application
import android.widget.Toast

object ToastUtil {
    private lateinit var toastMessage : Toast

    fun showShortToast(application: Application, string: String){
        if(this::toastMessage.isInitialized){
            toastMessage.cancel()
        }
        toastMessage = Toast.makeText(application, string, Toast.LENGTH_SHORT)
        toastMessage.show()
    }
}