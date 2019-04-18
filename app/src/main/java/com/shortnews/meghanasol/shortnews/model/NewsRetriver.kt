package com.shortnews.meghanasol.shortnews.model

import android.util.Log
import com.shortnews.meghanasol.shortnews.callbacks.NewsRetriverCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetriver(val newsRetriverCallback: NewsRetriverCallback) {

    private lateinit var newsApiCall: NewsApiCall


    companion object {
        val TAG = this.javaClass.simpleName
        val baseUrl = "https://newsapi.org/v2/"
        val apiKey = "3d33118618934544aaf144b4432bdd03"
    }

    init {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        newsApiCall = retrofit.create(NewsApiCall::class.java)
    }

    /**
     * To Retrieve All
     * News Sources
     */
    fun getAllNewsResources() {
        val call = newsApiCall.getNewsSources(apiKey)
        Log.w("NewsRetriver", "***** getAllNewsResources ******")
        Log.w("NewsRetriver", "Url Pinged :: ${call.request().url()}")



        call.enqueue(object : Callback<SourceList> {
            override fun onFailure(call: Call<SourceList>, t: Throwable) {
                Log.w("NewsRetriver", "Error ---> ${t.printStackTrace()}")
            }

            override fun onResponse(call: Call<SourceList>, response: Response<SourceList>) {
                val sourceList = response.body()
                Log.w("NewsRetriver", "Status :: ${sourceList?.status}")

                if (sourceList != null) {
                    val newsProviderInfos: List<NewsProviderInfo> = sourceList.sources
                    for (newsProviderInfo in newsProviderInfos) {
                        if (newsProviderInfo.category == "general") Log.w(
                            "NewsRetriver",
                            "id :: ${newsProviderInfo.id}  Category :: ${newsProviderInfo.category}  Country :: ${newsProviderInfo.country}"
                        )
                    }
                }


            }
        })
    }

    /**
     * To Get All Top
     * HeadLines
     */
    fun getAllTopHeadLines() {
        val call = newsApiCall.getTopHeadline("in", apiKey)

        Log.w("NewsRetriver", "*** getAllTopHeadLines ***")
        Log.w("NewsRetriver", "*** getAllTopHeadLines ***")
        call.enqueue(object : Callback<TopHeadLineSource> {
            override fun onFailure(call: Call<TopHeadLineSource>, t: Throwable) {
                Log.w("NewsRetriver", "Error" + t.printStackTrace())
            }

            override fun onResponse(call: Call<TopHeadLineSource>, response: Response<TopHeadLineSource>) {
                val topHeadLineSource = response.body()
                if (topHeadLineSource != null) {
                    Log.w(
                        "NewsRetriver",
                        "Status :: ${topHeadLineSource.status}   TotalResult : ${topHeadLineSource.totalResults}"
                    )
                    val newsArticles = topHeadLineSource.articles
                    // CallbackApp
                    newsRetriverCallback.listofArticlesRetrived(topHeadLineSource.articles)
                }
            }
        })


    }
}