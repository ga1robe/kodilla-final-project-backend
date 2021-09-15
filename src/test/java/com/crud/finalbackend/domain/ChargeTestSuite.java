package com.crud.finalbackend.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ChargeTestSuite {

    private ChargeStatus chargeStatus;

    @Test
    public void testCharge(){
        //Given
        Charge charge = new Charge(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1));
        //When
        //Then
        assertEquals(chargeStatus.FREE, charge.getStatus());
        assertEquals(BigDecimal.ZERO, charge.getValue());
        assertEquals(LocalDate.of(1970, 1, 1), charge.getChargeDate());

    }

    @Test
    public void test2Charges(){
        //Given
        Charge charge1 = new Charge(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1));
        Charge charge2 = new Charge(1L, chargeStatus.FREE, BigDecimal.ZERO, LocalDate.of(1970, 1, 1));
        //When
        //Then
        assertEquals(true, charge1.equals(charge2));
    }

}