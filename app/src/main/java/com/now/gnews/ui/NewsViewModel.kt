package com.now.gnews.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.now.gnews.GnewsApplication
import com.now.gnews.models.ApiResponse
import com.now.gnews.repository.NewsRepo
import com.now.gnews.wrapper.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(application: Application,val newsRepo: NewsRepo) : AndroidViewModel(application) {


  val allNews: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    var pageNumber = 1

    val CategoryNews: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    var CategoryPageNumber = 1

    init {
        getAllNews("us")
    }


    fun getAllNews(country: String) = viewModelScope.launch {
        allNews.postValue(Resource.Loading())
        if(hasConnection()){
            try {
                val response = newsRepo.getAllNews(country, pageNumber)
                allNews.postValue(handleAllNewsResponse(response))
            } catch (t: Throwable) {
                when(t) {
                    is IOException -> allNews.postValue(Resource.Error("Network Error"))
                }
            }
        } else {
            allNews.postValue(Resource.Error("No Internet Connection"))
        }


    }

    fun getNewsFromCategory(country: String, category: String) = viewModelScope.launch {
        CategoryNews.postValue(Resource.Loading())
       if(hasConnection()) {

           try {

               val response = newsRepo.getNewsFromCat(country, CategoryPageNumber, category)
               CategoryNews.postValue(handleCategoryNewsResponse(response))

           } catch (t: Throwable){
               when(t) {
                   is IOException -> CategoryNews.postValue(Resource.Error(" Network Failed"))
               }
           }
       } else {
           CategoryNews.postValue(Resource.Error(" No Internet Connection"))
       }
    }


    private fun handleAllNewsResponse(response: Response<ApiResponse>) : Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                return Resource.Success(results)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCategoryNewsResponse(response: Response<ApiResponse>) : Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                return Resource.Success(results)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasConnection(): Boolean {

        val connectivityManager = getApplication<GnewsApplication>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork?: return false
            val capabilities = connectivityManager
                .getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return  when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }

        return false
    }
}