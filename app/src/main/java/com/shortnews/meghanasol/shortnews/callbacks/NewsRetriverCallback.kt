package com.shortnews.meghanasol.shortnews.callbacks

import com.shortnews.meghanasol.shortnews.model.NewsArticles

interface NewsRetriverCallback{
    fun listofArticlesRetrived( listOfArticles : List<NewsArticles>)
}