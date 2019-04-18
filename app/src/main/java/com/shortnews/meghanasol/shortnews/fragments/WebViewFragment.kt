package com.shortnews.meghanasol.shortnews.fragments

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.shortnews.meghanasol.shortnews.MainActivity
import com.shortnews.meghanasol.shortnews.R
import com.shortnews.meghanasol.shortnews.base.BaseFragment
import java.lang.ClassCastException

class WebViewFragment : BaseFragment() {

    lateinit var webView: WebView
    lateinit var url :String
    lateinit var rooActivity: MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity){
            rooActivity = context
        }else{
            throw ClassCastException()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
            url = arguments.getString("url")
            Log.w("WebViewFragment","${url}" )
        }
        Log.w("WebViewFragment","Outside if")
    }



    override fun injectLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rooActivity.setToolbarConfig("Web View Fragment")
        return inflater?.inflate(R.layout.webview_fragment, container, false)
    }

    @TargetApi(Build.VERSION_CODES.O)
    override fun initView(view: View, savedInstanceState: Bundle?) {
        webView = view?.findViewById<WebView>(R.id.webView)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.loadsImagesAutomatically = true
        webView.webViewClient = WebViewClient()
        webView?.loadUrl(url)

    }


}