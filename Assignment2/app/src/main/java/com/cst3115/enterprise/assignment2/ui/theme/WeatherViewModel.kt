package com.cst3115.enterprise.assignment2.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {
    private val _weatherState = mutableStateOf<WeatherResponse?>(null)
    val weatherState: State<WeatherResponse?> = _weatherState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        val city = preferencesManager.getCity()
        if (city.isNotEmpty()) {
            fetchWeather(city)
        }
    }


    private fun fetchWeather(city: String) {
        viewModelScope.launch {
            _isLoading.value = true
                try {
                    val response = RetrofitClient.weatherApi.getCurrentWeather(
                        city = city,
                        apiKey = "db06ea2482d0c84ceb590af37f635817"
                    )
                    _weatherState.value = response
                    preferencesManager.saveWeatherData(response)
                    _error.value = null
                } catch (e: Exception) {
                    _error.value = e.message
                    _weatherState.value = preferencesManager.getWeatherData()
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }