package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserDtoTestSuite {

    @Test
    public void testUserDto(){
        //Given
        UserDto testUser = UserDto.builder()
                .id(1L)
                .name("name 1")
                .surname("surname 1")
                .email("name1.surname1@test.com")
                .registered(LocalDate.of(2021,9,11).toString())
                .securePassword("123456789")
                .preferenceIds(new HashSet<>())
                .build();
        //When
        //Then
        assertEquals(1L, testUser.getId());
        assertEquals("name 1", testUser.getName());
        assertEquals("surname 1", testUser.getSurname());
        assertEquals("name1.surname1@test.com", testUser.getEmail());
        assertEquals(LocalDate.of(2021,9,11).toString(), testUser.getRegistered());
        assertEquals("123456789", testUser.getSecurePassword());
        assertEquals(0, testUser.getPreferenceIds().size());
    }

    @Test
    public void test2UserDto(){
        //Given
        UserDto testUser1 = UserDto.builder()
                .id(1L)
                .name("name 1")
                .surname("surname 1")
                .email("name1.surname1@test.com")
                .registered(LocalDate.of(2021,9,11).toString())
                .securePassword("123456789")
                .preferenceIds(new HashSet<>())
                .build();
        UserDto testUser2 = UserDto.builder()
                .id(2L)
                .name("name 2")
                .surname("surname 2")
                .email("name2.surname2@test.com")
                .registered(LocalDate.of(2020,8,12).toString())
                .securePassword("1234567890")
                .preferenceIds(new HashSet<>())
                .build();

        //When
        //Then
        assertEquals(false, testUser1.equals(testUser2));
    }
}