package com.example.newzapp.domains

import com.example.newzapp.models.ResponseNews
import com.example.newzapp.models.ResponseNewsItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApiInterface {

    @GET("countryApi.php")
    fun getEveryData(
    @Query("country")country:String = "in"
    ): Call<List<ResponseNewsItem>>

    @GET("searchApi.php")
    fun getSearchData(
        @Query("q")q:String
    ): Call<List<ResponseNewsItem>>

}