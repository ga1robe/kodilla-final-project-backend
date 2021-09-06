package com.crud.finalbackend.domain;

import com.crud.finalbackend.config.AdminConfig;
import com.crud.finalbackend.scheduler.PreferencesScheduler;
import com.crud.finalbackend.service.MailSentRecordService;
import com.crud.finalbackend.service.SeasonalTrailOffersService;
import com.crud.finalbackend.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrailOfferTest {
//    @InjectMocks
//    private PreferencesScheduler preferencesScheduler;
//    @Mock
//    private MailSentRecordService mailSentRecordService;
    @Mock
    private AdminConfig adminConfig;
//    @Mock
//    private SimpleEmailService simpleEmailService;
    @Mock
    private SeasonalTrailOffersService seasonalTrailOffers;

    @Test
    void getThereHikingTrail() {
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
}