package com.crud.finalbackend.domain;

import com.crud.finalbackend.config.AdminConfig;
import com.crud.finalbackend.service.SeasonalTrailOffersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrailOfferTest {
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SeasonalTrailOffersService seasonalTrailOffers;

    @Test
    void testThereHikingTrail() {
        //Given
        User testUser = User.builder()
                .email("test@test.com")
                .build();
        Preference testPreference = Preference.builder()
                .user(testUser)
                .build();
        TrailOffer testOffer = new TrailOffer();
        testOffer.setTotalDistance( BigInteger.TEN );
        testOffer.setReturnHikingTrail( new HikingTrailConnectionWithWeather() );
        testOffer.setThereHikingTrail( new HikingTrailConnectionWithWeather() );
        Map<Preference, TrailOffer> testMap = new HashMap<>();
        testMap.put(testPreference, testOffer);

        //When
        //Then
        assertNotEquals(new HikingTrailConnectionWithWeather(), testOffer.getThereHikingTrail());
    }

    @Test
    void getReturnHikingTrail() {
        //Given
        User testUser = User.builder()
                .email("test@test.com")
                .build();
        Preference testPreference = Preference.builder()
                .user(testUser)
                .build();
        TrailOffer testOffer = new TrailOffer();
        testOffer.setTotalDistance( BigInteger.TEN );
        testOffer.setReturnHikingTrail( new HikingTrailConnectionWithWeather() );
        testOffer.setThereHikingTrail( new HikingTrailConnectionWithWeather() );
        Map<Preference, TrailOffer> testMap = new HashMap<>();
        testMap.put(testPreference, testOffer);

        //When
        //Then
        assertNotEquals(new HikingTrailConnectionWithWeather(), testOffer.getReturnHikingTrail());
    }

    @Test
    void getTotalDistance() {
        User testUser = User.builder()
                .email("test@test.com")
                .build();
        Preference testPreference = Preference.builder()
                .user(testUser)
                .build();
        TrailOffer testOffer = new TrailOffer();
        testOffer.setTotalDistance( BigInteger.TEN );
        testOffer.setReturnHikingTrail( new HikingTrailConnectionWithWeather() );
        testOffer.setThereHikingTrail( new HikingTrailConnectionWithWeather() );
        Map<Preference, TrailOffer> testMap = new HashMap<>();
        testMap.put(testPreference, testOffer);

        //When
        //Then
        assertEquals(BigInteger.TEN, testOffer.getTotalDistance());
    }

    @Test
    void test2ThereHikingTrails() {
        //Given
        User testUser = User.builder()
                .email("test@test.com")
                .build();
        Preference testPreference1 = Preference.builder()
                .user(testUser)
                .build();
        Preference testPreference2 = Preference.builder()
                .user(testUser)
                .build();
        TrailOffer testOffer1 = new TrailOffer();
        TrailOffer testOffer2 = new TrailOffer();
        testOffer1.setTotalDistance( BigInteger.TEN );
        testOffer2.setTotalDistance( BigInteger.TEN );
        testOffer1.setReturnHikingTrail( new HikingTrailConnectionWithWeather() );
        testOffer2.setReturnHikingTrail( new HikingTrailConnectionWithWeather() );
        testOffer1.setThereHikingTrail( new HikingTrailConnectionWithWeather() );
        testOffer2.setThereHikingTrail( new HikingTrailConnectionWithWeather() );
        Map<Preference, TrailOffer> testMap1 = new HashMap<>();
        Map<Preference, TrailOffer> testMap2 = new HashMap<>();
        testMap1.put(testPreference1, testOffer1);
        testMap2.put(testPreference2, testOffer2);

        //When
        //Then
        assertEquals(false, testMap1.equals(testMap2));
    }
}