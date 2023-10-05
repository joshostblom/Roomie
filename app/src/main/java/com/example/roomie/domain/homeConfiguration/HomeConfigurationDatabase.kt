package com.example.roomie.domain.homeConfiguration

import android.content.Context
import com.google.gson.Gson

class HomeConfigurationDatabase
    (
    val context: Context,
) {
    fun getConfiguration(): HomeConfiguration {
        val sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        return Gson().fromJson(sharedPref.getString("homeConfig", ""), HomeConfiguration::class.java) ?: HomeConfiguration()
    }

    fun setConfiguration(
        homeConfig: HomeConfiguration,
    ) {
        val sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPref.edit().putString("homeConfig", Gson().toJson(homeConfig)).apply()
    }
}