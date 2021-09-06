package com.crud.finalbackend.external.api.hereApi.domain;

import com.crud.finalbackend.external.api.hereApi.HereApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HereApiLocationTestSuite {

    @Autowired
    private HereApiClient hereApiClient;

    @Test
    void getAddress() {
        //Given & When
        List<HereApiLocation> response = hereApiClient.searchLocations(52.26816, 20.46321, "Kampinos", "PL");
        HereLocationProperties address = response.get(0).getAddress();
        //Then
        assertEquals("Kampinos, Woj. Mazowieckie, Polska",response.get(0).getAddress().getLabel());
    }

    @Test
    void getPosition() {
        //Given & When
        List<HereApiLocation> response = hereApiClient.searchLocations(52.26847, 20.45933, "Kampinos", "PL");
        HereApiPosition position = response.get(0).getPosition();
        //Then
        assertEquals(52.26847,position.getLatitude());
        assertEquals(20.45933,position.getLongitude());
    }
}