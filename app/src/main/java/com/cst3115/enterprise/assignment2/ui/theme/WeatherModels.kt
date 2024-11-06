package com.cst3115.enterprise.assignment2.ui.theme

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>
)

data class Current(
    val temp: Double,
    val humidity: Int,
    val weatherDescription: String
)

data class Daily(
    val dt: Long,
    val temp: DayTemp,
    val weather: List<Weather>
)

data class DayTemp(
    val day: Double,
    val min: Double,
    val max: Double
)

data class Weather(
    val description: String,
    val icon: String
)