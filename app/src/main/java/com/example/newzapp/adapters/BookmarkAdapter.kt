package com.example.newzapp.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newzapp.R
import com.example.newzapp.activities.NewsActivity
import com.example.newzapp.databinding.NewsSampleBinding
import com.example.newzapp.dbs.NewsDbs.Companion.dbs
import com.example.newzapp.dbs.NewsDbs.Companion.initDatabase
import com.example.newzapp.models.NewsEntity
import com.example.newzapp.models.ResponseNewsItem

class BookmarkAdapter(var list: MutableList<NewsEntity>) :
    RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {
    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = NewsSampleBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_sample, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {

        Glide.with(holder.itemView).load(list[position].newsImage).into(holder.binding.newsImage)
        holder.binding.newsTitle.text = list[position].newsTitle
        holder.binding.newsAuthor.text = list[position].newsAuthor
        holder.binding.publishedDate.text = list[position].newsPublishDate


        holder.binding.newsSample.setOnLongClickListener {
            val dialog = Dialog(holder.itemView.context)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.dialog_view)
            dialog.setCanceledOnTouchOutside(true)

            val delete: TextView = dialog.findViewById(R.id.deleteBtn)
            val cancel: TextView = dialog.findViewById(R.id.cancelBtn)

            dialog.show()

            delete.setOnClickListener {
                initDatabase(holder.itemView.context).dao().deleteData(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
                dialog.dismiss()
            }
            cancel.setOnClickListener {
                dialog.dismiss()
            }
            true
        }

        holder.binding.newsSample.setOnClickListener {
            val intent = Intent(holder.itemView.context, NewsActivity::class.java)

            intent.putExtra("newsTitle", list[position].newsTitle)
            intent.putExtra("newsDescription", list[position].newsDescription)
            intent.putExtra("newsImage", list[position].newsImage)
            intent.putExtra("newsContent", list[position].newsCategory)
            intent.putExtra("newsAuthor", list[position].newsAuthor)
            intent.putExtra("newsDate", list[position].newsAuthor)

            holder.itemView.context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bookmarkData(newsList: MutableList<NewsEntity>) {
        list = newsList
        notifyDataSetChanged()
    }

}