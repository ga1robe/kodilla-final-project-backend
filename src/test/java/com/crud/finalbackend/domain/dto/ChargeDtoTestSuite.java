package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.Charge;
import com.crud.finalbackend.domain.ChargeStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ChargeDtoTestSuite {

    private ChargeStatus chargeStatus;

    @Test
    public void testChargeDto(){
        //Given
        ChargeDto charge = new ChargeDto(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1).toString());
        //When
        //Then
        assertEquals(chargeStatus.FREE, charge.getStatus());
        assertEquals(BigDecimal.ZERO, charge.getValue());
        assertEquals(LocalDate.of(1970, 1, 1).toString(), charge.getChargeDate());

    }

    @Test
    public void test2Charges(){
        //Given
        ChargeDto charge1 = new ChargeDto(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1).toString());
        ChargeDto charge2 = new ChargeDto(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1).toString());
        //When
        //Then
        assertEquals(false, charge1.equals(charge2));
    }

}