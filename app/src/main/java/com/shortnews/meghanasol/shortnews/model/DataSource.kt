package com.shortnews.meghanasol.shortnews.model

data  class SourceList (val status:String,val sources : List<NewsProviderInfo>)
data class NewsProviderInfo(val id : String,val name : String,val description : String,val url : String,val category :String,val language :String,val country:String)

data  class TopHeadLineSource(val status: String,val totalResults : Int,val articles : List<NewsArticles>)
data class NewsArticles(val newsSource: NewsSource,val author:String,val title :String,val description:String,val url:String,val urlToImage :String,val publishedAt:String,val content :String)
data class NewsSource(val id: String,val name: String)
