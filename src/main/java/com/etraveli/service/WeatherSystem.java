package com.etraveli.service;

import com.etraveli.model.WeatherResponseDTO;

public interface WeatherSystem {
    WeatherResponseDTO getCurrentWeatherDetails(String city, String state, String uri);
     void weatherReportSystem();
}
