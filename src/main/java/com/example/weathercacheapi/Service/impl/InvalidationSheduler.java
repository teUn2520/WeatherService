package com.example.weathercacheapi.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InvalidationSheduler {

    @Autowired
    private WeatherServiceImpl service;

    @Scheduled(cron = "*/10 * * * *")
    public void invalidateCache() {
        service.clearWeatherCache();
    }
}
