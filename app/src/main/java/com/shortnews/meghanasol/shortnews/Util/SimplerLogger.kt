package com.shortnews.meghanasol.shortnews.Util

import android.util.Log


/**
 * Simple Logger Class
 * To Log
 * Might change afterwards
 */
class SimplerLogger {

    companion object {
        var prefix: String = "sub"


    }

    fun e(message: String) {
        Log.e(prefix, " :: ${message}")
    }

    fun v(message: String) {
        Log.v(prefix, " :: ${message}")
    }

    fun w(message: String) {
        Log.w(prefix, " :: ${message}")
    }

    fun d(message: String) {
        Log.d(prefix, " :: ${message}")
    }

}