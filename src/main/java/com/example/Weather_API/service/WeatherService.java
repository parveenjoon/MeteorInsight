package com.example.Weather_API.service;

import com.example.Weather_API.exception.RapidApiException;
import com.example.Weather_API.exception.WeatherServiceException;
import com.example.Weather_API.model.HourlyWeather;
import com.example.Weather_API.model.WeatherSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private RapidApiService rapidApiService;

    public WeatherSummary getWeatherSummary(String city) {
        try {
            return rapidApiService.getWeatherSummary(city);
        } catch (RapidApiException ex) {
            throw new WeatherServiceException("Error fetching weather summary from RapidAPI: " + ex.getMessage());
        }
    }

    public HourlyWeather getHourlyWeather(String city) {
        try {
            return rapidApiService.getHourlyWeather(city);
        } catch (RapidApiException ex) {
            throw new WeatherServiceException("Error fetching hourly weather forecast from RapidAPI: " + ex.getMessage());
        }
    }
}
