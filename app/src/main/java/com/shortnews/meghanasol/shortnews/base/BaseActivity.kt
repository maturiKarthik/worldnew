package com.shortnews.meghanasol.shortnews.base

import android.app.Fragment
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import com.shortnews.meghanasol.shortnews.R
import com.shortnews.meghanasol.shortnews.Util.SimplerLogger
import com.shortnews.meghanasol.shortnews.Util.SimplerLogger.Companion.prefix

/**
 * By defalut all
 * Classes in Kotline are final
 * open
 */

open class BaseActivity : AppCompatActivity() {
    lateinit var logger: SimplerLogger

    init {
        logger = SimplerLogger()
        logger.apply { prefix = "Base Activity" }
    }


    override fun onStart() {
        super.onStart()
        logger.d("App Started ${isNetworkConnected()}")
    }

    override fun onPause() {
        super.onPause()
        logger.d("On Pause ${isNetworkConnected()}")
    }

    override fun onResume() {
        super.onResume()
        logger.d("On Resume ${isNetworkConnected()}")
    }

    override fun onStop() {
        super.onStop()
        logger.d("On Stop ${isNetworkConnected()}")

    }

    override fun onRestart() {
        super.onRestart()
        logger.d("On Restart ${isNetworkConnected()}")
    }

    /**
     * Replacement Of Fragment
     */
    fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }

    /**
     * Check Network Connectivity
     * ConnectivityManager connectivityManager
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) true else false
    }
}