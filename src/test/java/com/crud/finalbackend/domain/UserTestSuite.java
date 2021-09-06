package com.crud.finalbackend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTestSuite {

    @Test
    void addPreference() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals(0, user.getPreferences().size());
    }

    @Test
    void removePreference() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        Preference preference = new Preference();
        preferences.add(preference);
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        user.removePreference(preference);
        //Then
        assertEquals(0, user.getPreferences().size());
    }

    @Test
    void removeAllPreferences() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        Preference preference1 = new Preference();
        Preference preference2 = new Preference();
        preferences.add(preference1);
        preferences.add(preference2);
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        user.removeAllPreferences();
        //Then
        assertEquals(0, user.getPreferences().size());
    }

    @Test
    void getName() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals("Test1", user.getName());
    }

    @Test
    void getSurname() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals("Test1", user.getSurname());
    }

    @Test
    void getEmail() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals("test1.test1@test1.com", user.getEmail());
    }

    @Test
    void getSecurePassword() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals("123456789", user.getSecurePassword());
    }

    @Test
    void getRegistered() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        LocalDate registered = LocalDate.now();
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals(registered, user.getRegistered());
    }

    @Test
    void getPreferences() {
        //Given
        @NotNull Set<Preference> preferences = new HashSet<>();
        Preference preference1 = new Preference();
        Preference preference2 = new Preference();
        preferences.add(preference1);
        preferences.add(preference2);
        User user = new User(1L, "Test1", "Test1", "test1.test1@test1.com", "123456789", LocalDate.now(), preferences);
        //When;
        //Then
        assertEquals(2, user.getPreferences().size());
    }
}