package com.shortnews.meghanasol.shortnews

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.*
import android.util.Log

class SwipeController : Callback() {

    val TAG = "SwipeController"

    var swipeBack = true

    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
        Log.d(TAG, "getMovementFlags  ${p1}")
        // swipeBack = true
        return ItemTouchHelper.Callback.makeMovementFlags(0, LEFT )
    }

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
        Log.d("TAG", "On Swipped element ${p1}")
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }



}