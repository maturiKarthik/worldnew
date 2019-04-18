package com.shortnews.meghanasol.shortnews

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import com.shortnews.meghanasol.shortnews.base.BaseActivity
import com.shortnews.meghanasol.shortnews.fragments.More
import com.shortnews.meghanasol.shortnews.fragments.TopHeadLinesFragment

class MainActivity : BaseActivity() {


    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var countainerFragment: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.design_bottom_sheet)
        countainerFragment = findViewById<FrameLayout>(R.id.container)
        Log.w("MainActivity","On Create in the mainActivity")

        setToolbarConfig("Home Screen")

        Toast.makeText(this@MainActivity, "Network Connectivity :: ${isNetworkConnected()}", Toast.LENGTH_LONG).show()

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item?.itemId) {
                R.id.refreshFeed -> {
                    replaceFragment(TopHeadLinesFragment())
                    Toast.makeText(this@MainActivity, "On Refersh Freed", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.home -> {
                    Toast.makeText(this@MainActivity, "Home is clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.more -> {
                    replaceFragment(More())
                    true
                }
                else -> false

            }
        }


    }

    fun setToolbarConfig(title: String){
        //toolbar.setTitle(title)
        supportActionBar?.setTitle(title)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

}