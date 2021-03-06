package com.crud.finalbackend.external.api.weather;

import com.crud.finalbackend.external.api.weather.domain.WeatherForecast;
import com.crud.finalbackend.external.api.weather.mapper.WeatherForecastMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WeatherClientFacade {
    private final WeatherClient weatherClient;
    private final WeatherForecastMapper weatherForecastMapper;

    public WeatherForecast getWeatherForecast(String point) {
        return weatherForecastMapper.mapToWeatherForecast( weatherClient.getWeatherForecast(point) );
    }
}
