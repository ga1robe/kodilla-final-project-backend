package com.crud.finalbackend.except;

import com.crud.finalbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserNotFoundExceptionTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserNotFoundException() {
        //Given
        //When
        //Then
        assertThrows(UserNotFoundException.class, () -> userRepository.findById(2L).orElseThrow(UserNotFoundException::new));

    }

}