package com.now.gnews.repository

import com.now.gnews.retrofitFiles.retrofitSingleton

class NewsRepo {


    suspend fun getAllNews(country: String, page: Int) =
        retrofitSingleton.api.getAllNews(country, page)

    suspend fun getNewsFromCat(country: String, page: Int, category: String) =
        retrofitSingleton.api.getNewsFromCategory(country, page, category)
}




