package com.crud.finalbackend.external.api.weather.mapper;

import com.crud.finalbackend.external.api.weather.domain.DailyWeatherForecast;
import com.crud.finalbackend.external.api.weather.domain.dto.DailyWeatherForecastDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DailyWeatherForecastMapper {

    private DailyWeatherForecast mapToDailyWeatherForecast (DailyWeatherForecastDto dto) {
        return DailyWeatherForecast.builder()
                .maxTemperature( dto.getMaxTemperature() )
                .minTemperature( dto.getMinTemperature() )
                .expectedTemperature( dto.getExpectedTemperature() )
                .date( LocalDate.parse(dto.getDate()) )
                .build();
    }

    public List<DailyWeatherForecast> mapToDailyWeatherForecastList (List<DailyWeatherForecastDto> dtoList) {
        return dtoList.stream()
                .map( this::mapToDailyWeatherForecast )
                .collect(Collectors.toList());
    }
}
