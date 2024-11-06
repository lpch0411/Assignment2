package com.cst3115.enterprise.assignment2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cst3115.enterprise.assignment2.ui.theme.Current
import com.cst3115.enterprise.assignment2.ui.theme.Daily
import com.cst3115.enterprise.assignment2.ui.theme.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun MainScreen(navController: NavController, viewModel: WeatherViewModel) {
    val weatherState = viewModel.weatherState.value
    val isLoading = viewModel.isLoading.value
    val error = viewModel.error.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Weather App",
                style = MaterialTheme.typography.h5
            )
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(Icons.Default.Settings, "Settings")
            }
        }

        // Weather Content
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        error?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        weatherState?.let { weather ->
            CurrentWeather(weather.current)
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(weather.daily)
        }
    }
}

@Composable
fun CurrentWeather(current: Current) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${current.temp}°C",
                style = MaterialTheme.typography.h3
            )
            Text(
                text = current.weatherDescription,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Humidity: ${current.humidity}%",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun WeatherForecast(daily: List<Daily>) {
    LazyColumn {
        items(daily) { day ->
            DayForecast(day)
        }
    }
}

@Composable
fun DayForecast(day: Daily) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = SimpleDateFormat("EEE, MMM dd")
                    .format(Date(day.dt * 1000))
            )
            Text(text = "${day.temp.day}°C")
        }
    }
}