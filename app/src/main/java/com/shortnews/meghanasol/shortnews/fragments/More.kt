package com.shortnews.meghanasol.shortnews.fragments

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.card.MaterialCardView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.shortnews.meghanasol.shortnews.R
import com.shortnews.meghanasol.shortnews.Util.AboutPhone
import com.shortnews.meghanasol.shortnews.base.BaseFragment

class More : BaseFragment() {
    lateinit var aboutPhone: TextView
    lateinit var checkInternetSpeed: MaterialCardView
    lateinit var checkInternetConnection: MaterialCardView
    lateinit var countrySpinner : Spinner
    val country = listOf<String>("all","in","fr","us","dn")



    override fun injectLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.more_fragment, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        log("More has no view to inject")
        aboutPhone = view.findViewById<TextView>(R.id.aboutPhone)
        checkInternetSpeed = view.findViewById<MaterialCardView>(R.id.checkInternet)
        checkInternetConnection = view.findViewById<MaterialCardView>(R.id.checkInternetConnection)
        countrySpinner = view.findViewById(R.id.countrySpinner)


        val adapter = ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,country)
        countrySpinner.adapter = adapter

        aboutPhone.setText("${AboutPhone.getPhoneDetails()}")
        checkInternetSpeed.setOnClickListener{
            // Starting an Intent to Launch a browser
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("https://fast.com/"))
            startActivity(intentBrowser)
        }


        checkInternetConnection.setOnClickListener { BackGround().execute("", "", "") }


    }

    class BackGround : AsyncTask<String, String, Boolean>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): Boolean {
            return AboutPhone.isOnline()
        }

        override fun onPostExecute(result: Boolean?) {

            Log.w("TAG", "In My Background ${result}")
            super.onPostExecute(result)
        }
    }

}


