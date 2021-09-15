package com.crud.finalbackend.domain.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserListDtoTestSuite {

    @Test
    public void testUserListDto(){
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
        List<UserDto> testUserList = new ArrayList<UserDto>();
        testUserList.add(testUser);
        //When
        //Then
        assertEquals(1, testUserList.size());
        assertEquals(1L, testUserList.get(0).getId());
        assertEquals("name 1", testUserList.get(0).getName());
        assertEquals("surname 1", testUserList.get(0).getSurname());
        assertEquals("name1.surname1@test.com", testUserList.get(0).getEmail());
        assertEquals(LocalDate.of(2021,9,11).toString(), testUserList.get(0).getRegistered());
        assertEquals("123456789", testUserList.get(0).getSecurePassword());
        assertEquals(0, testUserList.get(0).getPreferenceIds().size());
    }

    @Test
    public void test2UserListDto(){
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
        List<UserDto> testUserList1 = new ArrayList<UserDto>();
        testUserList1.add(testUser1);
        List<UserDto> testUserList2 = new ArrayList<UserDto>();
        testUserList2.add(testUser2);

        //When
        //Then
        assertEquals(false, testUserList1.equals(testUserList2));
    }
}