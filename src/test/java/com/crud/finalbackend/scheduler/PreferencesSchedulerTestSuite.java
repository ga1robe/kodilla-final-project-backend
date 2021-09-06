package com.crud.finalbackend.scheduler;

import com.crud.finalbackend.config.AdminConfig;
import com.crud.finalbackend.domain.*;
import com.crud.finalbackend.service.MailSentRecordService;
import com.crud.finalbackend.service.SimpleEmailService;
import com.crud.finalbackend.service.SeasonalTrailOffersService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PreferencesSchedulerTestSuite {
    @InjectMocks
    private PreferencesScheduler preferencesScheduler;
    @Mock
    private MailSentRecordService mailSentRecordService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private SeasonalTrailOffersService seasonalTrailOffers;

    @Test
    public void testSendPreferencesEmail() {
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

        lenient().when(adminConfig.getApplicationEmail()).thenReturn("test@test.com");
        lenient().when(seasonalTrailOffers.getPreferencesAndOffers()).thenReturn( testMap );

        //When
        preferencesScheduler.notifyAboutOffers();
        //Then
        verify(simpleEmailService, times(0)).send(anyString(), any(Mail.class));
    }
}