package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.ServiceUsageRecord;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ServiceUsageRecordServiceTestSuite {
    @Autowired
    ServiceUsageRecordService service;

    @Before("*")
    public void cleanUp() {
        service.deleteAllRecords();
    }

    @Test
    public void testAddRecord() {
        //Given
        ServiceUsageRecord testRecord = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeClass" )
                .methodArgument( "Arguments" )
                .build();

        //When
        service.addRecord(testRecord);
        ServiceUsageRecord result = service.getAllRecords().get(0);

        //Then
        assertEquals(testRecord, result);

        //cleanUp
        service.deleteAllRecords();
    }

    @Test
    public void getAllRecords() {
        //Given
        ServiceUsageRecord testRecord = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeClass" )
                .methodArgument( "Arguments" )
                .build();
        ServiceUsageRecord testRecordTwo = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeOtherClass" )
                .methodArgument( "Something" )
                .build();

        List<ServiceUsageRecord> records = Arrays.asList(testRecord, testRecordTwo);
        service.addRecord(testRecord);
        service.addRecord(testRecordTwo);

        //When
        List<ServiceUsageRecord> result = service.getAllRecords();

        //Then
        assertEquals(records, result);

        //cleanUp
        service.deleteAllRecords();
    }

    @Test
    public void getRecordsOfMethod() {
        //Given
        ServiceUsageRecord testRecord = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeClass" )
                .methodArgument( "Arguments" )
                .build();
        ServiceUsageRecord testRecordTwo = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeOtherClass" )
                .methodArgument( "Something" )
                .build();
        ServiceUsageRecord testRecordThree = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeClass" )
                .methodArgument( "Something" )
                .build();

        service.addRecord(testRecord);
        service.addRecord(testRecordTwo);
        service.addRecord(testRecordThree);

        //When
        List<ServiceUsageRecord> result = service.getRecordsOfMethod("SomeClass");

        //Then
        assertEquals(2, result.size());
        assertFalse(result.contains(testRecordTwo));

        //cleanUp
        service.deleteAllRecords();
    }

    @Test
    public void deleteAllRecords() {
        //Given
        ServiceUsageRecord testRecord = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeClass" )
                .methodArgument( "Arguments" )
                .build();
        ServiceUsageRecord testRecordTwo = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeOtherClass" )
                .methodArgument( "Something" )
                .build();
        ServiceUsageRecord testRecordThree = ServiceUsageRecord.builder()
                .whenExecuted( LocalDateTime.now() )
                .serviceClass( "SomeClass" )
                .methodArgument( "Something" )
                .build();

        service.addRecord(testRecord);
        service.addRecord(testRecordTwo);
        service.addRecord(testRecordThree);

        //When
        service.deleteAllRecords();
        List<ServiceUsageRecord> result = service.getAllRecords();

        //Then
        assertEquals(0, result.size());
    }
}