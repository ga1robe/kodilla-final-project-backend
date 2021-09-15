package com.crud.finalbackend.domain.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PreferenceListDtoTestSuite {

    @Test
    public void testPreferenceDto(){
        //Given
        PreferenceDto preferenceDto = new PreferenceDto(1L, 1L, "Point 1", "Point 2", BigInteger.TEN, 12);
        List<PreferenceDto> preferenceListDto = new ArrayList<PreferenceDto>();
        preferenceListDto.add(preferenceDto);
        //When
        //Then
        assertEquals(1, preferenceListDto.size());
        assertEquals(1L, preferenceListDto.get(0).getId());
        assertEquals(1L, preferenceListDto.get(0).getUserId());
        assertEquals("Point 1", preferenceListDto.get(0).getTrailBegin());
        assertEquals("Point 2", preferenceListDto.get(0).getTrailEnd());
        assertEquals(BigInteger.TEN, preferenceListDto.get(0).getDistance());
        assertEquals(12, preferenceListDto.get(0).getMinTemperature());
    }

    @Test
    public void test2PreferenceDto(){
        //Given
        PreferenceDto preferenceDto1 = new PreferenceDto(1L, 1L, "Point 1", "Point 2", BigInteger.TEN, 12);
        PreferenceDto preferenceDto2 = new PreferenceDto(2L, 2L, "Point 3", "Point 4", BigInteger.valueOf(15), 10);
        List<PreferenceDto> preferenceListDto1 = new ArrayList<PreferenceDto>();
        List<PreferenceDto> preferenceListDto2 = new ArrayList<PreferenceDto>();
        preferenceListDto1.add(preferenceDto1);
        preferenceListDto2.add(preferenceDto2);
        //When
        //Then
        assertEquals(false, preferenceListDto1.equals(preferenceListDto2));
    }

}