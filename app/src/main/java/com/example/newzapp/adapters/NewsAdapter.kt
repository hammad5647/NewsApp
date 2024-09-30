package com.example.newzapp.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newzapp.R
import com.example.newzapp.activities.NewsActivity
import com.example.newzapp.databinding.NewsSampleBinding
import com.example.newzapp.dbs.NewsDbs.Companion.initDatabase
import com.example.newzapp.models.ResponseNewsItem

class NewsAdapter(private var list: MutableList<ResponseNewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NewsSampleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_sample, parent, false)
        return NewsViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchFilter(filteredList: String?) {
        list = filteredList as MutableList<ResponseNewsItem>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(list[position].imageUrl)
            .into(holder.binding.newsImage)

        holder.binding.newsTitle.text = list[position].title
        holder.binding.newsAuthor.text = list[position].author
        holder.binding.publishedDate.text = list[position].publishDate


        holder.binding.newsSample.setOnClickListener {
            val intent = Intent(holder.itemView.context, NewsActivity::class.java)

            intent.putExtra("newsTitle", list[position].title)
            intent.putExtra("newsDescription", list[position].description)
            intent.putExtra("newsImage", list[position].imageUrl)
            intent.putExtra("newsContent", list[position].category)
            intent.putExtra("newsAuthor", list[position].author)
            intent.putExtra("newsDate", list[position].publishDate)

            holder.itemView.context.startActivity(intent)
        }
    }
}