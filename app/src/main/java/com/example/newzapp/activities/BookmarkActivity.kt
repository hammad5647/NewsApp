package com.example.newzapp.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newzapp.R
import com.example.newzapp.adapters.BookmarkAdapter
import com.example.newzapp.adapters.NewsAdapter
import com.example.newzapp.databinding.ActivityBookmarkBinding
import com.example.newzapp.dbs.NewsDbs.Companion.initDatabase
import com.example.newzapp.models.NewsEntity
import com.example.newzapp.models.ResponseNewsItem

class BookmarkActivity : AppCompatActivity() {
    var list = mutableListOf<NewsEntity>()
    private lateinit var binding : ActivityBookmarkBinding
    private lateinit var adapter : BookmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        adapter = BookmarkAdapter(list)
        binding.bookmarkRecyclerView.adapter = adapter

        binding.backBtn.setOnClickListener{
            finish()
        }
    }
    override fun onResume() {

        list = initDatabase(this).dao().readData()
        adapter.bookmarkData(list)
        binding.bookmarkImage.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        binding.bookmarkRecyclerView.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
        super.onResume()
    }
}