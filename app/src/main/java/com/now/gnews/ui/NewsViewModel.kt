package com.now.gnews.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.now.gnews.models.ApiResponse
import com.now.gnews.repository.NewsRepo
import com.now.gnews.wrapper.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepo: NewsRepo) : ViewModel() {


  val allNews: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    var pageNumber = 1

    init {
        getAllNews("us")
    }


    fun getAllNews(country: String) = viewModelScope.launch {
        allNews.postValue(Resource.Loading())
        val response = newsRepo.getAllNews(country, pageNumber)
        allNews.postValue(handleAllNewsResponse(response))
    }

    private fun handleAllNewsResponse(response: Response<ApiResponse>) : Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                return Resource.Success(results)
            }
        }
        return Resource.Error(response.message())
    }
}