package com.crud.finalbackend.except;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.repository.ChargeRepository;
import com.crud.finalbackend.repository.PreferenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PreferenceNotFoundExceptionTestSuite {

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Test
    public void TestPreferenceNotFoundException() {
        //Given
        //When
        //Then
        assertThrows(PreferenceNotFoundException.class, () -> preferenceRepository.findById(2L).orElseThrow(PreferenceNotFoundException::new));

    }

}