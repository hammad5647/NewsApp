package com.example.newzapp.domains

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedHelper {
    @SuppressLint("CommitPrefEdits", "ApplySharedPref")

    fun setCountry(country: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences("SelectedCountry", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("country", country)
        edit.commit()
    }

    fun getCountry(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("SelectedCountry", MODE_PRIVATE)
        return sharedPreferences.getString("country", null)
    }


    @SuppressLint("ApplySharedPref")
    fun setTheme(theme: Boolean, context: Context) {
        val sharedPreferences = context.getSharedPreferences("SelectedTheme", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putBoolean("theme", theme)
        edit.commit()
    }

    fun getTheme(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("SelectedTheme", MODE_PRIVATE)
        return sharedPreferences.getBoolean("theme", false)
    }

}