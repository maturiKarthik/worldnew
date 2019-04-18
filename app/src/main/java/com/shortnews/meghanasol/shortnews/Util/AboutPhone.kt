package com.shortnews.meghanasol.shortnews.Util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class AboutPhone {

    companion object {

        // Metheod to detect wether devices is connected to online.
        fun isOnline(): Boolean {
            var sucess = false
            try {
                val url = URL("http://www.google.com")
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.connectTimeout = 1000
                httpURLConnection.connect()
                sucess = (httpURLConnection.responseCode == 200)
                Log.w("online", "${httpURLConnection.responseCode}")
            } catch (e: Exception) {
                Log.w("online", "falsepp")
                e.printStackTrace()
            }
            return sucess
        }

        fun getPhoneDetails(): String {

            val about =
                "Device : ${Build.DEVICE}\n model : ${Build.MODEL}-${Build.MANUFACTURER} \n OS: ${Build.VERSION.CODENAME} ${Build.VERSION.CODENAME} \nESN : ${Build.PRODUCT} "

            return about
        }
    }


    // Metheod to  detect the wifi and 3G on/off
    fun isNetworkConnected(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) true else false
    }

}