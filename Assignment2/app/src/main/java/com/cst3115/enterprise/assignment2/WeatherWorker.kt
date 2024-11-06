package com.cst3115.enterprise.assignment2

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cst3115.enterprise.assignment2.ui.theme.RetrofitClient
import com.cst3115.enterprise.assignment2.ui.theme.WeatherResponse
import com.google.gson.Gson

class WeatherWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val sharedPreferences = context.getSharedPreferences(
        "WeatherAppPrefs",
        Context.MODE_PRIVATE
    )

    override suspend fun doWork(): Result {
        return try {
            val city = sharedPreferences.getString("selected_city", "") ?: ""
            if (city.isNotEmpty()) {
                val response = RetrofitClient.weatherApi.getCurrentWeather(
                    city = city,
                    apiKey = "YOUR_API_KEY"
                )
                // Store the updated weather data
                updateLocalWeatherData(response)
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun updateLocalWeatherData(weatherResponse: WeatherResponse) {
        val gson = Gson()
        sharedPreferences.edit()
            .putString("weather_data", gson.toJson(weatherResponse))
            .putLong("last_update", System.currentTimeMillis())
            .apply()
    }
}