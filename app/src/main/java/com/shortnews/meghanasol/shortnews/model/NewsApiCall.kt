package com.shortnews.meghanasol.shortnews.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiCall {

    // Api call to get All news interface
    @GET("sources")
    fun getNewsSources(@Query("apiKey") apiKey: String): Call<SourceList>

    //Api call to get Top Headlines top-headlines?country=us&apiKey=3d33118618934544aaf144b4432bdd03
    @GET("top-headlines")
    fun getTopHeadline(@Query("country") country : String,@Query("apiKey")apiKey: String) : Call<TopHeadLineSource>

}