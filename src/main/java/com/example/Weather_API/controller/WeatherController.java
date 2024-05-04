package com.example.Weather_API.controller;

import com.example.Weather_API.exception.WeatherServiceException;
import com.example.Weather_API.model.HourlyWeather;
import com.example.Weather_API.model.WeatherSummary;
import com.example.Weather_API.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/summary/{city}")
    public ResponseEntity<?> getWeatherSummary(@PathVariable String city) {
        try {
            WeatherSummary summary = weatherService.getWeatherSummary(city);
            return ResponseEntity.ok(summary);
        } catch (WeatherServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving weather summary: " + ex.getMessage());
        }
    }

    @GetMapping("/hourly/{city}")
    public ResponseEntity<?> getHourlyWeather(@PathVariable String city) {
        try {
            HourlyWeather hourlyWeather = weatherService.getHourlyWeather(city);
            return ResponseEntity.ok(hourlyWeather);
        } catch (WeatherServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving hourly weather forecast: " + ex.getMessage());
        }
    }
}
