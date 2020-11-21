package com.now.gnews.repository

import com.now.gnews.retrofitFiles.RetrofitSingleton

class NewsRepo {


    suspend fun getAllNews(country: String, page: Int) =
        RetrofitSingleton.api.getAllNews(country, page)

    suspend fun getNewsFromCat(country: String, page: Int, category: String) =
        RetrofitSingleton.api.getNewsFromCategory(country, page, category)
}




