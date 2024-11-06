package com.cst3115.enterprise.assignment2.ui.theme

import android.content.Context
import com.google.gson.Gson

class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        "WeatherAppPrefs",
        Context.MODE_PRIVATE
    )

    fun getCity(): String {
        return sharedPreferences.getString("selected_city", "") ?: ""
    }

    fun saveWeatherData(weatherResponse: WeatherResponse) {
        val gson = Gson()
        sharedPreferences.edit()
            .putString("weather_data", gson.toJson(weatherResponse))
            .putLong("last_update", System.currentTimeMillis())
            .apply()
    }

    fun getWeatherData(): WeatherResponse? {
        val weatherJson = sharedPreferences.getString("weather_data", null)
        return if (weatherJson != null) {
            Gson().fromJson(weatherJson, WeatherResponse::class.java)
        } else null
    }
}