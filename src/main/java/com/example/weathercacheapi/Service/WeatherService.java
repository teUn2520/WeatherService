package com.example.weathercacheapi.Service;

import com.example.weathercacheapi.DTO.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeather(double lat, double lon);
    void clearWeatherCache();
}