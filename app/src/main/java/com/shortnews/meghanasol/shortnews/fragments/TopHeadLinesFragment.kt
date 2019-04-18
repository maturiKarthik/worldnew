package com.shortnews.meghanasol.shortnews.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.shortnews.meghanasol.shortnews.MainActivity
import com.shortnews.meghanasol.shortnews.R
import com.shortnews.meghanasol.shortnews.TopHeadLinesAdapter
import com.shortnews.meghanasol.shortnews.base.BaseFragment
import com.shortnews.meghanasol.shortnews.callbacks.NewsRetriverCallback
import com.shortnews.meghanasol.shortnews.model.NewsArticles
import com.shortnews.meghanasol.shortnews.model.NewsRetriver

class TopHeadLinesFragment : BaseFragment(), NewsRetriverCallback {


    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    //Local Variable to Store Articls
    var listOfArticlesLocal: List<NewsArticles>? = null
    lateinit var rootActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("TopHeadLinesFragment","On Created Insdie TopHeadline FRagment")

        NewsRetriver(this).getAllTopHeadLines()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            rootActivity = context
        } else {
            throw ClassCastException()
        }
    }

    override fun injectLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootActivity.setToolbarConfig("News Feed App")
        return inflater.inflate(R.layout.headlines_top_fragment, container, false)
    }


    override fun initView(view: View, savedInstanceState: Bundle?) {


        Log.w("TopHeadLinesFragment", "On Init View")

        progressBar = view.findViewById(R.id.progressBar)
        Log.w("TopHeadLinesFragment", "listOfArticlesLocal   outside")
        if (listOfArticlesLocal != null) {
            Log.w("TopHeadLinesFragment", "listOfArticlesLocal   size ${listOfArticlesLocal?.size}")
            progressBar.visibility = View.GONE
            initRecyclerView(listOfArticlesLocal)
        }

        /**
         * Adding Swipe Controller
         */

        // val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(SwipeController())
        //itemTouchHelper.attachToRecyclerView(recyclerView)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.refreshFeed -> {
                Handler().postDelayed({
                    Log.w("TopHeadLinesFragment", "Retry after 3000 sec")
                    progressBar.visibility = View.VISIBLE
                    NewsRetriver(this).getAllTopHeadLines()

                }, 3000)
            }
        }
        return true
    }

    fun initRecyclerView(listOfArticles: List<NewsArticles>?) {

        if (listOfArticles != null) {
            recyclerView = view.findViewById(R.id.topHeadLines)
            recyclerView.apply {
                visibility = View.VISIBLE
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = TopHeadLinesAdapter(listOfArticles, rootActivity)
            }
        }

    }

    /**
     * Callback called From Model when
     * Data is rerived...
     */
    override fun listofArticlesRetrived(listOfArticles: List<NewsArticles>) {
        listOfArticlesLocal = listOfArticles
        Log.w("TopHeadLinesFragment", "listOfArticlesLocal  inside callback size ${listOfArticlesLocal?.size}")
        // listOfArticlesLocal = listOfArticles
        progressBar.apply {
            visibility = View.GONE
        }

        initRecyclerView(listOfArticles)
        Log.w("TopHeadLinesFragment", "Entered In To Callback")


    }


}
