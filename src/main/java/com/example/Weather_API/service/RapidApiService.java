package com.example.Weather_API.service;

import com.example.Weather_API.exception.RapidApiException;
import com.example.Weather_API.model.HourlyWeather;
import com.example.Weather_API.model.WeatherSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RapidApiService {

    @Value("${rapidapi.apikey}")
    private String apiKey;

    @Value("${rapidapi.endpoint.weatherSummary}")
    private String weatherSummaryEndpoint;

    @Value("${rapidapi.endpoint.hourlyWeather}")
    private String hourlyWeatherEndpoint;

    private final RestTemplate restTemplate;

    public RapidApiService() {
        this.restTemplate = new RestTemplate();
    }

    public WeatherSummary getWeatherSummary(String city) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<WeatherSummary> response = restTemplate.exchange(
                    weatherSummaryEndpoint, HttpMethod.GET, entity, WeatherSummary.class, city);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RapidApiException("Failed to fetch weather summary. Status code: " + response.getStatusCodeValue());
            }
        } catch (Exception ex) {
            throw new RapidApiException("Error fetching weather summary from RapidAPI", ex);
        }
    }

    public HourlyWeather getHourlyWeather(String city) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<HourlyWeather> response = restTemplate.exchange(
                    hourlyWeatherEndpoint, HttpMethod.GET, entity, HourlyWeather.class, city);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RapidApiException("Failed to fetch hourly weather forecast. Status code: " + response.getStatusCodeValue());
            }
        } catch (Exception ex) {
            throw new RapidApiException("Error fetching hourly weather forecast from RapidAPI", ex);
        }
    }
}
