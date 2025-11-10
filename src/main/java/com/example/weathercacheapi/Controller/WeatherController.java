package com.example.weathercacheapi.Controller;

import com.example.weathercacheapi.DTO.GeoLocation;
import com.example.weathercacheapi.DTO.WeatherResponse;
import com.example.weathercacheapi.Service.GeoLocationService;
import com.example.weathercacheapi.Service.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
@Slf4j
public class WeatherController {
    private final WeatherService weatherService;
    private final GeoLocationService geoLocationService;

    @GetMapping("getweather")
    public WeatherResponse getWeatherStatus(HttpServletRequest request) {
        GeoLocation location = geoLocationService.getLocationByIp(request);
        log.info("Location is" + location.getLon() + location.getLat());
        return weatherService.getWeather(location.getLat(), location.getLon());
    }
}
