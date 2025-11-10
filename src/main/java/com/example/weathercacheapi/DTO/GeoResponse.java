package com.example.weathercacheapi.DTO;

import lombok.Data;

@Data
public class GeoResponse {
    private double lat;
    private double lon;
    private String city;
    private String country;
}

