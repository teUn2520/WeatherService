package com.example.weathercacheapi.Service.impl;

import com.example.weathercacheapi.DTO.WeatherResponse;
import com.example.weathercacheapi.Service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
@Primary
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.timeout}")
    private int timeout;

    @Autowired
    public WeatherServiceImpl(@Value("${weather.api.key}") String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    @Override
    @CachePut(value = "weather", unless = "#result == null or #result.isEmpty()")
    public WeatherResponse getWeather(double lat, double lon) {
        String url = buildWeatherUrl(lat, lon);
        WeatherResponse weatherAnswer;

        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
        weatherAnswer = response.getBody();

        return weatherAnswer;
    }

    @Override
    @CacheEvict("weather")
    public void clearWeatherCache() {}

    private String buildWeatherUrl(double lat, double lon) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();
    }
}
