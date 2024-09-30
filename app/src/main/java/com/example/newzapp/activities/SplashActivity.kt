package com.example.newzapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newzapp.MainActivity
import com.example.newzapp.R
import com.example.newzapp.domains.SharedHelper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        val sharedHelper = SharedHelper()
        val country = sharedHelper.getCountry(this)

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "$country", Toast.LENGTH_SHORT).show()
            if (country != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("country", country)
                startActivity(intent)
            }
            else{
            val intent = Intent(this, CountryActivity::class.java)
            startActivity(intent)
            }

        }, 3000)
    }
}