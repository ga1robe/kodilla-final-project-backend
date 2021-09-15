package com.crud.finalbackend.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HikingTrailConnectionWithWeatherTestSuite {

    @Test
    public void testHikingTrailConnectionWithWeather(){
        //Given
        HikingTrailConnectionWithWeather hikingTrailConnectionWithWeather = new HikingTrailConnectionWithWeather(new Preference(1L, null, "point 1", "point 2", BigInteger.TEN, 12), BigDecimal.TEN);
        //When
        //Then
        assertEquals(BigDecimal.TEN, hikingTrailConnectionWithWeather.getExpectedTemperature());
        assertEquals(null, hikingTrailConnectionWithWeather.getPreference().getUser());
        assertEquals("point 1", hikingTrailConnectionWithWeather.getPreference().getTrailBegin());
        assertEquals("point 2", hikingTrailConnectionWithWeather.getPreference().getTrailEnd());
        assertEquals(BigInteger.TEN, hikingTrailConnectionWithWeather.getPreference().getDistance());
        assertEquals(12, hikingTrailConnectionWithWeather.getPreference().getMinTemperature());
    }

    @Test
    public void test2HikingTrailConnectionWithWeather(){
        //Given
        HikingTrailConnectionWithWeather hikingTrailConnectionWithWeather1 = new HikingTrailConnectionWithWeather(new Preference(1L, null, "point 1", "point 2", BigInteger.TEN, 12), BigDecimal.TEN);
        HikingTrailConnectionWithWeather hikingTrailConnectionWithWeather2 = new HikingTrailConnectionWithWeather(new Preference(1L, null, "point 1", "point 2", BigInteger.TEN, 12), BigDecimal.TEN);
        //When
        //Then
        assertEquals(0, hikingTrailConnectionWithWeather1.compareTo(hikingTrailConnectionWithWeather2));
    }

}