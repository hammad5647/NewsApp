package com.example.newzapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.newzapp.activities.BookmarkActivity
import com.example.newzapp.activities.ChannelActivity
import com.example.newzapp.activities.SearchActivity
import com.example.newzapp.adapters.NewsAdapter
import com.example.newzapp.databinding.ActivityMainBinding
import com.example.newzapp.databinding.CountrySelectLayoutBinding
import com.example.newzapp.domains.NewsApiClient.Companion.getNews
import com.example.newzapp.domains.NewsApiInterface
import com.example.newzapp.domains.SharedHelper
import com.example.newzapp.models.ResponseNewsItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedHelper: SharedHelper
    private var country: String = ""
    private var imagelist = mutableListOf<CarouselItem>()
    private lateinit var newsAdapter: NewsAdapter
    var list = mutableListOf<ResponseNewsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        sharedHelper = SharedHelper()
        country = sharedHelper.getCountry(this)!!
        setTheme()
        clickInit()
        imagelist.add(CarouselItem(R.drawable.india))
        imagelist.add(CarouselItem(R.drawable.pakistan))
        imagelist.add(CarouselItem(R.drawable.us))
        imagelist.add(CarouselItem(R.drawable.australia))
        imagelist.add(CarouselItem(R.drawable.srilanka))
        imagelist.add(CarouselItem(R.drawable.uae))

        binding.imageCarousel.setData(imagelist)
        binding.imageCarousel.autoPlay = true
        binding.imageCarousel.autoPlayDelay = 1800


        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            sharedHelper.setTheme(isChecked, this)
            applyTheme(isChecked)
            binding.main.closeDrawers()
        }
        getNewsData()

        val newsApiInterface = getNews().create(NewsApiInterface::class.java)
        newsApiInterface.getEveryData(country = country).enqueue(object : Callback<List<ResponseNewsItem>> {
            override fun onResponse(
                call: Call<List<ResponseNewsItem>>,
                response: Response<List<ResponseNewsItem>>
            ) {
                Log.e("data===", "onResponse: $response")
                if (response.isSuccessful) {
                    list = response.body()!! as MutableList
                    Log.d("Success", "onResponse: $list")
                    newsAdapter = NewsAdapter(list)
                    binding.mainRecyclerView.adapter = newsAdapter
                } else {
                    Log.e("Success Error", "onResponse: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ResponseNewsItem>>, t: Throwable) {
                Log.e("Error", "onFailure: ${t.message} ")
            }
        })
    }

    private fun getNewsData() {
        var intent: Intent = getIntent()
        country = intent.getStringExtra("country").toString()
    }

    @SuppressLint("ResourceType")
    private fun clickInit() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchBtn -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.bookmarkBtn -> startActivity(Intent(this, BookmarkActivity::class.java))
                R.id.channelBtn -> startActivity(Intent(this, ChannelActivity::class.java))
            }
            false
        }
        binding.menuBtn.setOnClickListener {
            binding.main.open()
        }
        binding.dialogBtn.setOnClickListener {
            showMenu(v = it)
        }
    }

    private fun showMenu(v: View) {
        val dialogBinding: CountrySelectLayoutBinding =
            CountrySelectLayoutBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(dialogBinding.root)
        dialog.setCanceledOnTouchOutside(true)

        dialogBinding.checkIndia.setOnCheckedChangeListener { buttonView, isChecked ->
            country = "in"
            dialogBinding.checkUS.isChecked = false
            dialogBinding.checkPakistan.isChecked = false
            dialogBinding.checkUAE.isChecked = false
            dialogBinding.checkAustralia.isChecked = false
            dialogBinding.checkSriLanka.isChecked = false

        }

        dialogBinding.checkUS.setOnCheckedChangeListener { buttonView, isChecked ->

            country = "us"
            dialogBinding.checkIndia.isChecked = false
            dialogBinding.checkUAE.isChecked = false
            dialogBinding.checkPakistan.isChecked = false
            dialogBinding.checkAustralia.isChecked = false
            dialogBinding.checkSriLanka.isChecked = false
        }
        dialogBinding.checkUAE.setOnCheckedChangeListener { buttonView, isChecked ->

            country = "are"
            dialogBinding.checkUS.isChecked = false
            dialogBinding.checkIndia.isChecked = false
            dialogBinding.checkPakistan.isChecked = false
            dialogBinding.checkAustralia.isChecked = false
            dialogBinding.checkSriLanka.isChecked = false
        }
        dialogBinding.checkPakistan.setOnCheckedChangeListener { buttonView, isChecked ->
            country = "pk"

            dialogBinding.checkUS.isChecked = false
            dialogBinding.checkUAE.isChecked = false
            dialogBinding.checkIndia.isChecked = false
            dialogBinding.checkAustralia.isChecked = false
            dialogBinding.checkSriLanka.isChecked = false
        }
        dialogBinding.checkAustralia.setOnCheckedChangeListener { buttonView, isChecked ->
            country = "aus"
            dialogBinding.checkUS.isChecked = false
            dialogBinding.checkUAE.isChecked = false
            dialogBinding.checkPakistan.isChecked = false
            dialogBinding.checkIndia.isChecked = false
            dialogBinding.checkSriLanka.isChecked = false
        }
        dialogBinding.checkSriLanka.setOnCheckedChangeListener { buttonView, isChecked ->
            country = "lka"
            dialogBinding.checkUS.isChecked = false
            dialogBinding.checkUAE.isChecked = false
            dialogBinding.checkPakistan.isChecked = false
            dialogBinding.checkAustralia.isChecked = false
            dialogBinding.checkIndia.isChecked = false
        }

        sharedHelper.setCountry(country = country,this)
        Log.e("Country", "showMenu: $country", )
        val cancelBtn: TextView = dialog.findViewById(R.id.cancelBtn)
        val okBtn: TextView = dialog.findViewById(R.id.okBtn)

        dialog.show()

        okBtn.setOnClickListener {
            sharedHelper.setCountry(country, this)
            dialog.dismiss()

        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun applyTheme(theme: Boolean) {
        if (theme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setTheme() {
        val theme = sharedHelper.getTheme(this)

        if (theme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}