package com.crud.finalbackend.external.api.hereApi;

import com.crud.finalbackend.domain.ChargeStatus;
import com.crud.finalbackend.domain.dto.HikingTrailDto;
import com.crud.finalbackend.domain.dto.LocationDto;
import com.crud.finalbackend.external.api.hereApi.domain.HereApiLocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HereApiClientTestSuite {
    @Autowired
    private HereApiClient hereApiClient;

    @Test
    public void getPointGeocodeIsNotZeroZeroTest() {
        //When
        List<Double> position = hereApiClient.getPointGeocode("Warsaw, Poland");
        System.out.println("Position:" + position);
        //Then
        assertEquals(2, position.size());
        assertNotEquals(0.0, position.get(0));
        assertNotEquals(0.0, position.get(1));
    }

    @Test
    public void getPointGeocodeOfWarsawTest() {
        //When
        List<Double> position = hereApiClient.getPointGeocode("Warsaw, Poland");
        System.out.println("Position:" + position);
        //Then
        assertEquals(2, position.size());
        assertEquals(52.2356, position.get(0));
        assertEquals(21.01037, position.get(1));
    }

    @Test
    public void searchLocationsOfKampinosTest() {
        //When
        List<HereApiLocation> response = hereApiClient.searchLocations(52.26816, 20.46321, "Kampinos", "PL");
        //Then
        assertTrue(response.size() > 0);
    }

    @Test
    public void searchRouteFromKampinosToZaborowLength() {
        //Given
        LocationDto departure = new LocationDto(1L, "departure", "PL", 52.268164, 20.463206);
        LocationDto destination = new LocationDto(2L, "destination", "PL", 52.262327, 20.677063);
        HikingTrailDto hikingTrail = new HikingTrailDto(departure, destination, "Kampinos", "Zaborow", null, null, ChargeStatus.FREE);
        //When
        Integer length = hereApiClient.searchRouteLength(hikingTrail);
        System.out.println("Route length: " + length);
        // Then
        assertEquals(14858, length);
    }
}