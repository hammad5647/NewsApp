package com.example.newzapp.models

import com.google.gson.annotations.SerializedName

data class ResponseNews(

	@field:SerializedName("ResponseNews")
	val responseNews: List<ResponseNewsItem?>? = null
)

data class ResponseNewsItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("publishDate")
	val publishDate: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
