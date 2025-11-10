package com.example.weathercacheapi.Service.impl;

import com.example.weathercacheapi.DTO.GeoLocation;
import com.example.weathercacheapi.DTO.GeoResponse;
import com.example.weathercacheapi.Service.GeoLocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Primary
public class GeoLocationServiceImpl implements GeoLocationService {
    private final RestTemplate restTemplate;

    @Override
    public GeoLocation getLocationByIp(HttpServletRequest request) {
        String ip = getClientIpAdress(request);

        String url = "http://ip-api.com/json/" + ip;

        GeoResponse response = restTemplate.getForObject(url, GeoResponse.class);

        return new GeoLocation(response.getLat(), response.getLon());
    }

    private String getClientIpAdress(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }

        return request.getRemoteAddr();
    }
}
