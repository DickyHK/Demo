package com.demo.currencylist.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream

object CommonUtil {
    fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}