package com.example.newzapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newzapp.adapters.NewsAdapter
import com.example.newzapp.databinding.ActivityChannelBinding
import com.example.newzapp.domains.NewsApiClient.Companion.getNews
import com.example.newzapp.domains.NewsApiInterface
import com.example.newzapp.models.ResponseNews
import com.example.newzapp.models.ResponseNewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChannelActivity : AppCompatActivity() {

    private var source: String = "cnn"
    private lateinit var binding: ActivityChannelBinding
    lateinit var newsAdapter: NewsAdapter
    var list = mutableListOf<ResponseNewsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChannelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickInit()
//        val newsApiInterface = getNews().create(NewsApiInterface::class.java)
//        newsApiInterface.getSourceData(source = source).enqueue(object : Callback<ResponseNews> {
//            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
//                if (response.isSuccessful) {
//                    list = response.body()!!.articles as MutableList<ArticlesItem>
//                    newsAdapter = NewsAdapter(list)
//                    binding.channelRecyclerView.adapter = newsAdapter
//
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
//            }
//        })
    }

    private fun clickInit() {
        binding.cnnNews.setOnClickListener {
            source = "cnn"
        }
        binding.reuterNews.setOnClickListener {
            source = "reuters"
        }
        binding.foxNews.setOnClickListener {
            source = "fox-news"
        }
        binding.espnNews.setOnClickListener {
            source = "espn"
        }

    }

}