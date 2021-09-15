package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTestSuite {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;

    @Test
    public void testAddUser() {
        //Given
        User testUser = User.builder()
                .name("test1")
                .surname("test1")
                .email("test1@test.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();

        //When
        userService.addUser(testUser);

        //Then
        assertEquals(1, userService.getAllUsers().size());

        //clean up
        userService.deleteAllUsers();
    }

    @Test
    public void testAddUserWithTooShortName() {
        //Given, When & Then
        User testUser = User.builder()
                .name("T")
                .surname("Test1")
                .email("test1@test.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        try {
            userService.addUser(testUser);
        }
        catch (Exception e){
            e.getMessage();
        }
        finally {
            //clean up
            userService.deleteAllUsers();
        }
    }

    @Test
    public void testAddUserWithTooShortSurname() {
        //Given, When & Then
        User testUser = User.builder()
                .name("test1")
                .surname("T")
                .email("test1@test.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        try {
            userService.addUser(testUser);
        }
        catch (Exception e){
            e.getMessage();
        }
        finally {
            //clean up
            userService.deleteAllUsers();
        }
    }

    @Test
    public void testAddUserWithInvalidEmailAddress() {
        //Given, When & Then
        User testUser = User.builder()
                .name("test1")
                .surname("test1")
                .email("test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        try {
            userService.addUser(testUser);
        }
        catch (Exception e){
            e.getMessage();
        }
        finally {
            //clean up
            userService.deleteAllUsers();
        }
    }

    @Test
    public void testExistsByEmail() {
        //Given
        User testUser = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();

        //When
        userService.addUser(testUser);

        //Then
        assertTrue(userService.existsByEmail("test1@test.com"));

        //clean up
        userService.deleteAllUsers();
    }

    @Test
    public void testGetAllUsers() {
        User testUserOne = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserTwo = User.builder()
                .name("Test2")
                .surname("Test2")
                .email("test2@test2.up")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserThree = User.builder()
                .name("Test3")
                .surname("Test3")
                .email("no_mail@test3.ccc")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUserOne);
        userService.addUser(testUserTwo);
        userService.addUser(testUserThree);

        //When
        List<User> result = userService.getAllUsers();

        //Then
        assertEquals(3, result.size());
        assertTrue(result.contains(testUserOne));
        assertTrue(result.contains(testUserTwo));
        assertTrue(result.contains(testUserThree));

        //clean up
        userService.deleteAllUsers();
    }

    @Test
    public void testDeleteAllUsers() {
        User testUserOne = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserTwo = User.builder()
                .name("Test2")
                .surname("Test2")
                .email("test2@test2.up")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserThree = User.builder()
                .name("Test3")
                .surname("Test3")
                .email("no_mail@test3.ccc")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUserOne);
        userService.addUser(testUserTwo);
        userService.addUser(testUserThree);

        //When
        userService.deleteAllUsers();
        List<User> result = userService.getAllUsers();

        //Then
        assertEquals(0, result.size());

        //clean up
        userService.deleteAllUsers();
    }


    @Test
    public void testDeleteUserById() {
        User testUserOne = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserTwo = User.builder()
                .name("Test2")
                .surname("Test2")
                .email("test2@test2.up")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserThree = User.builder()
                .name("Test3")
                .surname("Test3")
                .email("no_mail@test3.ccc")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUserOne);
        userService.addUser(testUserTwo);
        userService.addUser(testUserThree);

        //When
        Long id = testUserTwo.getId();
        userService.deleteUserById(id);
        List<User> result = userService.getAllUsers();

        //Then
        assertEquals(2, result.size());
        assertTrue( result.contains(testUserOne) );
        assertFalse( result.contains(testUserTwo) );

        //clean up
        userService.deleteAllUsers();
    }

    @Test
    public void testDeleteUserWhenItHasPreferences() {
        User testUserOne = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserTwo = User.builder()
                .name("Test2")
                .surname("Test2")
                .email("test2@test2.up")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        User testUserThree = User.builder()
                .name("Test3")
                .surname("Test3")
                .email("no_mail@test3.ccc")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUserOne);
        userService.addUser(testUserTwo);
        userService.addUser(testUserThree);

        Preference testPreferenceOne = Preference.builder()
                .trailBegin("Kampinos")
                .trailEnd("Zaborow")
                .minTemperature(12)
                .distance(BigInteger.valueOf(14_858))
                .user(testUserOne)
                .build();
        Preference testPreferenceTwo = Preference.builder()
                .trailBegin("Kampinos")
                .trailEnd("Zaborow")
                .minTemperature(12)
                .distance(BigInteger.valueOf(14_858))
                .user(testUserTwo)
                .build();
        Preference testPreferenceThree = Preference.builder()
                .trailBegin("Kampinos")
                .trailEnd("Zaborow")
                .minTemperature(12)
                .distance(BigInteger.valueOf(14_858))
                .user(testUserThree)
                .build();
        preferenceService.addPreference(testPreferenceOne);
        preferenceService.addPreference(testPreferenceTwo);
        preferenceService.addPreference(testPreferenceThree);

        //When
        userService.deleteUserById(testUserOne.getId());
        int userNumber = userService.getAllUsers().size();
        int preferenceNumber = preferenceService.getAllPreferences().size();

        //Then
        assertEquals(2, userNumber);
        assertEquals(2, preferenceNumber);

        //clean up
        userService.deleteAllUsers();
    }

    @Test
    public void testGetUserByEmail() {
        User testUserOne = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUserOne);

        //When
        User result = userService.getUserByEmail("test1@test1.com");

        //Then
        assertEquals(testUserOne, result);

        //clean up
        userService.deleteAllUsers();
    }

    @Test
    public void testGetUserByNotExistingEmail() {
        //When, When & Then
        try {
            User result = userService.getUserByEmail("some@email.com");
        }
        catch (Exception e){
            e.getMessage();
        }
        finally {
            //clean up
            userService.deleteAllUsers();
        }
    }

    @Test
    public void testGetUserByNotExistingId() {
        //When, When & Then
        try {
            User result = userService.getUserById(12L);
        }
        catch (Exception e){
            e.getMessage();
        }
        finally {
            //clean up
            userService.deleteAllUsers();
        }
    }

    @Test
    public void testAddDuplicatedUser() {
        //When, When & Then
        User testUser1first = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        userService.addUser(testUser1first);
        User testUser1second = User.builder()
                .name("Test1")
                .surname("Test1")
                .email("test1@test1.com")
                .registered(LocalDate.now())
                .securePassword("123456789")
                .preferences(new HashSet<>())
                .build();
        try {
            userService.addUser(testUser1second);
        }
        catch (Exception e){
            e.getMessage();
        }
        finally {
            //clean up
            userService.deleteAllUsers();
        }

        //clean up
        userService.deleteAllUsers();
    }
}