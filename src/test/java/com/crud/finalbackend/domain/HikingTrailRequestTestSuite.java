package com.crud.finalbackend.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HikingTrailRequestTestSuite {

    @Test
    public void testHikingTrailRequest(){
        //Given
        HikingTrailRequest hikingTrailRequest = new HikingTrailRequest(1L, 52.268164, 20.463206, 52.262327, 20.677063, "departure", "destination", "PL", 15, LocalDate.of(2022, 9, 15));
        //When
        //Given
        assertEquals(1L, hikingTrailRequest.getId());
        assertEquals(52.268164, hikingTrailRequest.getDeparturePointLatitude());
        assertEquals(20.463206, hikingTrailRequest.getDeparturePointLongitude());
        assertEquals(52.262327, hikingTrailRequest.getDestinationPointLatitude());
        assertEquals(20.677063, hikingTrailRequest.getDestinationPointLongitude());
        assertEquals("departure", hikingTrailRequest.getTrailBegin());
        assertEquals("destination", hikingTrailRequest.getTrailEnd());
        assertEquals("PL", hikingTrailRequest.getCountryCode());
        assertEquals(15, hikingTrailRequest.getDistance());
        assertEquals(LocalDate.of(2022, 9, 15), hikingTrailRequest.getHikingDay());
    }

    @Test
    public void test2HikingTrailRequest(){
        //Given
        HikingTrailRequest hikingTrailRequest1 = new HikingTrailRequest(1L, 52.268164, 20.463206, 52.262327, 20.677063, "departure", "destination", "PL", 15, LocalDate.of(2022, 9, 15));
        HikingTrailRequest hikingTrailRequest2 = new HikingTrailRequest(1L, 52.268164, 20.463206, 52.262327, 20.677063, "departure", "destination", "PL", 15, LocalDate.of(2022, 9, 15));
        //When
        //Given
        assertEquals(true, hikingTrailRequest1.equals(hikingTrailRequest2));

    }

}