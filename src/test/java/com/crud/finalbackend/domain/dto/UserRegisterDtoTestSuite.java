package com.crud.finalbackend.domain.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserRegisterDtoTestSuite {
    @Test
    public void testUserDto(){
        //Given
        UserRegisterDto testUser = UserRegisterDto.builder()
                .name("name 1")
                .surname("surname 1")
                .email("name1.surname1@test.com")
                .securePassword("123456789")
                .build();
        //When
        //Then
        assertEquals("name 1", testUser.getName());
        assertEquals("surname 1", testUser.getSurname());
        assertEquals("name1.surname1@test.com", testUser.getEmail());
        assertEquals("123456789", testUser.getSecurePassword());
    }

    @Test
    public void test2UserDto(){
        //Given
        UserRegisterDto testUser1 = UserRegisterDto.builder()
                .name("name 1")
                .surname("surname 1")
                .email("name1.surname1@test.com")
                .securePassword("123456789")
                .build();
        UserRegisterDto testUser2 = UserRegisterDto.builder()
                .name("name 2")
                .surname("surname 2")
                .email("name2.surname2@test.com")
                .securePassword("1234567890")
                .build();

        //When
        //Then
        assertEquals(false, testUser1.equals(testUser2));
    }
}