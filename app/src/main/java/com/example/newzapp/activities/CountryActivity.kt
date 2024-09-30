package com.example.newzapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newzapp.MainActivity
import com.example.newzapp.databinding.ActivityCountryBinding
import com.example.newzapp.domains.SharedHelper

class CountryActivity : AppCompatActivity() {

    private lateinit var sharedHelper: SharedHelper
    private var countryName: String? = null
    private lateinit var binding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectCountry()
        clickInit()
        sharedHelper = SharedHelper()

    }

    private fun clickInit() {
        binding.nextBtn.setOnClickListener {
            if (countryName != null) {
                Toast.makeText(this, "$countryName", Toast.LENGTH_SHORT).show()
                sharedHelper.setCountry(countryName!!, this)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun selectCountry() {
        val checkBoxes = listOf(
            binding.checkBoxIndia,
            binding.checkBoxUS,
            binding.checkBoxPakistan,
            binding.checkBoxUAE,
            binding.checkBoxSriLanka,
            binding.checkBoxAustralia
        )

        val countries = listOf(
            binding.india,
            binding.unitedState,
            binding.pakistan,
            binding.unitedArab,
            binding.srilanka,
            binding.australia
        )

        countries.forEachIndexed { index, country ->
            country.setOnClickListener {
                checkBoxes.forEachIndexed { i, checkBox ->
                    if (i == index) {
                        // Toggle visibility and checked state for the clicked country
                        checkBox.isChecked = !checkBox.isChecked
                        when (index) {
                            0 -> {
                                countryName = "in"
                            }

                            1 -> {
                                countryName = "us"
                            }

                            2 -> {
                                countryName = "pk"
                            }

                            3 -> {
                                countryName = "are"
                            }

                            4 -> {
                                countryName = "lka"
                            }

                            5 -> {
                                countryName = "aus"
                            }
                        }
                        // Update the visibility of the corresponding checkbox
                        checkBox.visibility = if (checkBox.isChecked) {
                            binding.nextBtn.visibility = View.VISIBLE

                            View.VISIBLE
                        } else View.GONE
//                        //binding.nextBtn.visibility = View.GONE
                    } else {
                        // Hide and uncheck other checkboxes
                        checkBox.isChecked = false
                        checkBox.visibility = View.GONE

                    }
                }
            }
        }
    }
}
