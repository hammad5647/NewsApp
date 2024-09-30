package com.example.newzapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.newzapp.R
import com.example.newzapp.databinding.ActivityNewsBinding
import com.example.newzapp.dbs.DAO
import com.example.newzapp.dbs.NewsDbs
import com.example.newzapp.models.NewsEntity

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private var bookmark: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickInit()
        getIntentData()
    }

    private fun clickInit() {
        binding.backBtn.setOnClickListener {
            finish()
        }

    }

    private fun getIntentData() {
        val intent = intent

        val newsAuthor = intent.getStringExtra("newsAuthor")
        val newsDate = intent.getStringExtra("newsDate")
        val newsImage = intent.getStringExtra("newsImage")
        val newsTitle = intent.getStringExtra("newsTitle")
        val newsDescription = intent.getStringExtra("newsDescription")
        val newsContent = intent.getStringExtra("newsContent")

        binding.fullNewsAuthor.text = newsAuthor
        binding.fullNewsDate.text = newsDate
        Glide.with(this).load(newsImage).into(binding.newsMainImage)
        binding.fullNewsDescription.text = newsDescription
        binding.fullNewsTitle.text = newsTitle
        binding.fullNewsContent.text = newsContent


        binding.bookMarkBtn.setOnClickListener {
            if (bookmark == 0) {
                binding.bookMarkBtn.setImageResource(R.drawable.baseline_bookmark_1_24)
                Toast.makeText(this, "Added To Bookmark", Toast.LENGTH_SHORT).show()
                bookmark = 1

                val newsEntity = NewsEntity(newsTitle = newsTitle, newsImage = newsImage,newsPublishDate = newsDate, newsAuthor = newsAuthor)

                val dbs = NewsDbs.initDatabase(this)
                dbs.dao().insertData(newsEntity)

            } else {
                binding.bookMarkBtn.setImageResource(R.drawable.baseline_bookmark_border_24)
                Toast.makeText(this, "Removed From Bookmark", Toast.LENGTH_SHORT).show()
                bookmark = 0

            }
        }
    }
}