package com.crud.finalbackend.except;

import com.crud.finalbackend.external.api.weather.WeatherClientFacade;
import com.crud.finalbackend.external.api.weather.domain.DailyWeatherForecast;
import com.crud.finalbackend.repository.PreferenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UnableToGetWeatherForecastExceptionTest {

    @Test
    public void TestPreferenceNotFoundException() {
        //Given
        WeatherClientFacade weatherClientFacadeMock = mock(WeatherClientFacade.class);
        lenient().when(weatherClientFacadeMock.getWeatherForecast("Kampinos")).thenThrow(UnableToGetWeatherForecastException.class);
        //When
        //Then
        assertThrows(
                UnableToGetWeatherForecastException.class,
                () -> weatherClientFacadeMock.getWeatherForecast("Kampinos")
        );
    }
}