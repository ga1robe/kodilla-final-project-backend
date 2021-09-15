package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.ChargeStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ChargeListDtoTestSuite {

    private ChargeStatus chargeStatus;

    @Test
    public void testChargeListDto(){
        //Given
        ChargeDto charge = new ChargeDto(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1).toString());
        List<ChargeDto> chargeListDto = new ArrayList<ChargeDto>();
        chargeListDto.add(charge);

        //When
        //Then
        assertEquals(1, chargeListDto.size());


    }

    @Test
    public void test2ChargeLists(){
        //Given
        ChargeDto charge1 = new ChargeDto(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1).toString());
        ChargeDto charge2 = new ChargeDto(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1).toString());
        List<ChargeDto> chargeListDto1 = new ArrayList<ChargeDto>();
        List<ChargeDto> chargeListDto2 = new ArrayList<ChargeDto>();
        chargeListDto1.add(charge1);
        chargeListDto2.add(charge2);
        //When
        //Then
        assertEquals(true, chargeListDto1.equals(chargeListDto1));
    }
}