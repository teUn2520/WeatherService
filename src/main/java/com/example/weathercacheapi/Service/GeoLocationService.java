package com.example.weathercacheapi.Service;

import com.example.weathercacheapi.DTO.GeoLocation;
import jakarta.servlet.http.HttpServletRequest;

public interface GeoLocationService {
    GeoLocation getLocationByIp(HttpServletRequest request);
}
