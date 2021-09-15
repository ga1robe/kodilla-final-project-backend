package com.crud.finalbackend.external.api.weather;

import com.crud.finalbackend.external.api.weather.domain.WeatherForecast;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class WeatherClientFacadeTestSuite {
    @Autowired
    private WeatherClientFacade weatherClientFacade;

    @Test
    public void testGetWeatherForecast() {
        //Given
        WeatherForecast testForecast = weatherClientFacade.getWeatherForecast("Kampinos");

        //Then
        assertNotNull(testForecast);
        assertEquals("Kampinos", testForecast.getPoint());
        assertEquals(16, testForecast.getDailyForecasts().size());
        System.out.println( testForecast.getDailyForecasts().get(3).toString() );
    }
}