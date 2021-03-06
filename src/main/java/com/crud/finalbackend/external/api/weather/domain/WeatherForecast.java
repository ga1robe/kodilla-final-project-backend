package com.crud.finalbackend.external.api.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class WeatherForecast {
    private String point;
    private List<DailyWeatherForecast> dailyForecasts;
}
