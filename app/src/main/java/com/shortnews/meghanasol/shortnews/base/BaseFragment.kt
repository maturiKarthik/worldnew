package com.shortnews.meghanasol.shortnews.base

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open abstract class BaseFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return injectLayout(inflater, container, savedInstanceState)
        log("onCreatedView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        log("on View Created")
    }

    override fun onDetach() {
        super.onDetach()
        log("On Detach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        log("onActivityCreated")
    }

    abstract fun injectLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    abstract fun initView(view: View, savedInstanceState: Bundle?)

    fun log(message: String) {
        Log.w("BaseFragment", "${message}")
    }
}