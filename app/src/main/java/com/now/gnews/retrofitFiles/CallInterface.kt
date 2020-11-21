package com.now.gnews.retrofitFiles

import com.now.gnews.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CallInterface {

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q")
        country: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apikey")
        apikey: String = "3b3f5a9fc1074c238a5332b1127648b2"
    ) : Response<ApiResponse>

    @GET("v2/everything")
    suspend fun getNewsFromCategory(
        @Query("q")
        country: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("category")
        category: String,
        @Query("apikey")
        apikey: String = "3b3f5a9fc1074c238a5332b1127648b2"
    ) : Response<ApiResponse>
}