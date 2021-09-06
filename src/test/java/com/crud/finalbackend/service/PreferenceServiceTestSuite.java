package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PreferenceServiceTestSuite {
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private UserService userService;

    @Before("")
    public void cleanUp() {
        preferenceService.deleteAllPreferences();
        userService.deleteAllUsers();
    }

    @Test
    public void testAddPreference() {
        //Given
        User testUser = User.builder()
                .name("test")
                .surname("test")
                .email("test@test.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUser);

        Preference testPreference = Preference.builder()
                .trailBegin("Kampinos")
                .trailEnd("Zaborow")
                .minTemperature(12)
                .distance(BigInteger.valueOf(14_858))
                .user(testUser)
                .build();

        //When
        preferenceService.addPreference(testPreference);
        Long id = testPreference.getId();
        Preference result = preferenceService.getPreferenceById(id);

        //Then
        assertEquals(testPreference.getId(), result.getId());
        assertEquals(testPreference.getTrailBegin(), result.getTrailBegin());
        assertEquals(testPreference.getTrailEnd(), result.getTrailEnd());
        assertEquals(testPreference.getDistance(), result.getDistance());
        assertEquals(testPreference.getMinTemperature(), result.getMinTemperature());
        assertEquals(testPreference.getUser().getId(), result.getUser().getId());
        assertTrue( testUser.getPreferences().contains(testPreference) );
    }
}