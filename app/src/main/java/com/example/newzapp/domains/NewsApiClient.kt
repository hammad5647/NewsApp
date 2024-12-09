package com.example.newzapp.domains

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {


    companion object {
        private const val NEWSURL = "http://192.168.43.165/News/News/"
        private var retrofit: Retrofit? = null

        fun getNews(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(NEWSURL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    }
}