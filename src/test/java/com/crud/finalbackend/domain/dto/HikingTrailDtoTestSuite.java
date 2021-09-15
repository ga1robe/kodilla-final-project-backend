package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.ChargeStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HikingTrailDtoTestSuite {

    @Test
    public void testHikingTrailDto(){
        //Given
        //Given
        LocationDto departure = new LocationDto(1L, "departure", "PL", 52.268164, 20.463206);
        LocationDto destination = new LocationDto(2L, "destination", "PL", 52.262327, 20.677063);
        HikingTrailDto hikingTrail = new HikingTrailDto(departure, destination, "Kampinos", "Zaborow", ChargeStatus.FREE);
        //then
        assertEquals(ChargeStatus.FREE,hikingTrail.getCharge());
        assertEquals("Kampinos",hikingTrail.getDepartureName());
        assertEquals("Zaborow",hikingTrail.getDestinationName());
        assertEquals("PL",hikingTrail.getDeparturePoint().getCountryCode());
        assertEquals("PL",hikingTrail.getDestinationPoint().getCountryCode());
        assertEquals(1L,hikingTrail.getDeparturePoint().getId());
        assertEquals(2L,hikingTrail.getDestinationPoint().getId());
        assertEquals("departure",hikingTrail.getDeparturePoint().getLabel());
        assertEquals("destination",hikingTrail.getDestinationPoint().getLabel());
        assertEquals(52.268164,hikingTrail.getDeparturePoint().getLatitude());
        assertEquals(52.262327,hikingTrail.getDestinationPoint().getLatitude());
        assertEquals(20.463206,hikingTrail.getDeparturePoint().getLongitude());
        assertEquals(20.677063,hikingTrail.getDestinationPoint().getLongitude());
    }

    @Test
    public void test2HikingTrailDto(){
        //Given
        LocationDto departure = new LocationDto(1L, "departure", "PL", 52.268164, 20.463206);
        LocationDto destination = new LocationDto(2L, "destination", "PL", 52.262327, 20.677063);
        HikingTrailDto hikingTrail1 = new HikingTrailDto(departure, destination, "Kampinos", "Zaborow", ChargeStatus.FREE);
        HikingTrailDto hikingTrail2 = new HikingTrailDto(departure, destination, "Kampinos", "Zaborow", ChargeStatus.FREE);

        //When
        //then
        assertEquals(false,hikingTrail1.equals(hikingTrail2));
    }

}