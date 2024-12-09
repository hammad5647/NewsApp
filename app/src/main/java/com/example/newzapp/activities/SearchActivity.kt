package com.example.newzapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newzapp.adapters.NewsAdapter
import com.example.newzapp.databinding.ActivitySearchBinding
import com.example.newzapp.domains.NewsApiClient.Companion.getNews
import com.example.newzapp.domains.NewsApiInterface
import com.example.newzapp.models.ResponseNewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    var searchAdapter: NewsAdapter? = null
    var searchNews = "Breaking News"
    var list = mutableListOf<ResponseNewsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickInit()

    }
    private fun clickInit() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                apiCall(query!!)
                Log.e("Search Data", "onQueryTextSubmit: $searchNews", )
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchNews = searchAdapter?.searchFilter(newText).toString()
                }
                return true
            }

        })
        binding.backBtn.setOnClickListener{
            finish()
        }
    }

    fun apiCall(searchNews: String)
    {
        val newsApiInterface = getNews().create(NewsApiInterface::class.java)
        newsApiInterface.getSearchData(q =searchNews)
            .enqueue(object : Callback<List<ResponseNewsItem>> {
                override fun onResponse(
                    call: Call<List<ResponseNewsItem>>,
                    response: Response<List<ResponseNewsItem>>
                ) {
                    if (response.isSuccessful) {
                        list = response.body()!! as MutableList
                        searchAdapter = NewsAdapter(list)
                        binding.searchRecyclerView.adapter = searchAdapter
                        Log.e("Search Data", "onQueryTextSubmit: $list", )

                    }
                }

                override fun onFailure(call: Call<List<ResponseNewsItem>>, t: Throwable) {
                    Log.e("error", "onFailure: ${t.message}")
                }
            })
    }

}