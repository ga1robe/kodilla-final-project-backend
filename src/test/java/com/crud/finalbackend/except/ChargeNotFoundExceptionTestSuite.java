package com.crud.finalbackend.except;

import com.crud.finalbackend.repository.ChargeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ChargeNotFoundExceptionTestSuite {

    @Autowired
    private ChargeRepository chargeRepository;

    @Test
    public void TestChargeNotFoundException() {
        //Given
        //When
        //Then
        assertThrows(ChargeNotFoundException.class, () -> chargeRepository.findById(2L).orElseThrow(ChargeNotFoundException::new));

    }
}