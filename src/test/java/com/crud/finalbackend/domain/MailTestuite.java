package com.crud.finalbackend.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MailTestuite {

    @Test
    public void testMail() {
        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.pl")
                .toCc("test@test.pl")
                .subject("Test")
                .message("Test message")
                .build();
        //When
        //Then
        assertEquals("test@test.pl", mail.getMailTo());
        assertEquals("test@test.pl", mail.getToCc());
        assertEquals("Test", mail.getSubject());
        assertEquals("Test message", mail.getMessage());
    }

    @Test
    public void test2Mails() {
        //Given
        Mail mail1 = Mail.builder()
                .mailTo("test1@test.pl")
                .toCc("test@test.pl")
                .subject("Test")
                .message("Test message")
                .build();
        Mail mail2 = Mail.builder()
                .mailTo("test2@test.pl")
                .toCc("test@test.pl")
                .subject("Test")
                .message("Test message")
                .build();
        //When
        //Then
        assertEquals(false, mail1.equals(mail2));

    }

}