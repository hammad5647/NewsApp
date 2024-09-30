package com.example.newzapp.domains

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {


    companion object {
        private const val NEWSURL = "http://192.168.0.118/News/News/"
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