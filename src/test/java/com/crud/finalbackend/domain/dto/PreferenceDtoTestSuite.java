package com.crud.finalbackend.domain.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PreferenceDtoTestSuite {

    @Test
    public void testPreferenceDto(){
        //Given
        PreferenceDto preferenceDto = new PreferenceDto(1L, 1L, "Point 1", "Point 2", BigInteger.TEN, 12);
        //When
        //Then
        assertEquals(1L, preferenceDto.getId());
        assertEquals(1L, preferenceDto.getUserId());
        assertEquals("Point 1", preferenceDto.getTrailBegin());
        assertEquals("Point 2", preferenceDto.getTrailEnd());
        assertEquals(BigInteger.TEN, preferenceDto.getDistance());
        assertEquals(12, preferenceDto.getMinTemperature());
    }

    @Test
    public void test2PreferenceDto(){
        //Given
        PreferenceDto preferenceDto1 = new PreferenceDto(1L, 1L, "Point 1", "Point 2", BigInteger.TEN, 12);
        PreferenceDto preferenceDto2 = new PreferenceDto(2L, 2L, "Point 3", "Point 4", BigInteger.valueOf(15), 10);
        //When
        //Then
        assertEquals(false, preferenceDto1.equals(preferenceDto2));
    }
}