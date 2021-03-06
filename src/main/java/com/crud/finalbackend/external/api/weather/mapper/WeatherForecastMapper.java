package com.crud.finalbackend.external.api.weather.mapper;

import com.crud.finalbackend.external.api.weather.domain.dto.WeatherForecastDto;
import com.crud.finalbackend.external.api.weather.domain.WeatherForecast;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WeatherForecastMapper {
    private final DailyWeatherForecastMapper dailyWeatherForecastMapper;

    public WeatherForecast mapToWeatherForecast(WeatherForecastDto dto) {
        return WeatherForecast.builder()
                .point( dto.getPoint() )
                .dailyForecasts( dailyWeatherForecastMapper.mapToDailyWeatherForecastList( dto.getWeatherForecasts() ) )
                .build();
    }
}
