package com.shortnews.meghanasol.shortnews

import android.app.Activity
import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.design.card.MaterialCardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.shortnews.meghanasol.shortnews.Util.SimplerLogger.Companion.prefix
import com.shortnews.meghanasol.shortnews.fragments.TopHeadLinesFragment
import com.shortnews.meghanasol.shortnews.fragments.WebViewFragment
import com.shortnews.meghanasol.shortnews.model.NewsArticles
import com.squareup.picasso.Picasso

class TopHeadLinesAdapter(
    val newsArticles: List<NewsArticles>,
    val listener: MainActivity
) :
    RecyclerView.Adapter<MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topheadlines, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = newsArticles.size

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        if (newsArticles != null) {
            Log.w("TopHeadLinesAdapter","Total Size  :: > ${newsArticles.size}")

            Picasso.get().load(newsArticles.get(p1).urlToImage).placeholder(R.drawable.ic_launcher_foreground)
                .into(p0.image)
            p0.title.text = newsArticles.get(p1).title
            p0.description.text = newsArticles.get(p1).description
            /**
             * SetOnClickListener
             */
            p0.tapToRead.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("url",newsArticles.get(p1).url)
                val webViewFragment = WebViewFragment()
                webViewFragment.arguments = bundle
                listener.replaceFragment(webViewFragment)
              //  listener.urlToLoad(newsArticles.get(p1).url)
                Log.w("TAG", "${newsArticles.get(p1).url}")
            }

        }
    }

}


class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    var title: TextView
    var image: ImageView
    var description: TextView
    var cardView: MaterialCardView
    var tapToRead: MaterialButton


    init {
        title = view.findViewById(R.id.title)
        image = view.findViewById(R.id.imageView)
        description = view.findViewById(R.id.description)
        cardView = view.findViewById<MaterialCardView>(R.id.cardView)
        tapToRead = view.findViewById(R.id.tapToRead)
    }
}