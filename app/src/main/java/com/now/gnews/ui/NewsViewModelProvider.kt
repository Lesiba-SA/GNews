package com.now.gnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.now.gnews.repository.NewsRepo

class NewsViewModelProvider(val newsRepo: NewsRepo) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepo) as T
    }
}