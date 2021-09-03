package com.crud.finalbackend.mapper;


import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.User;
import com.crud.finalbackend.domain.dto.PreferenceDto;
import com.crud.finalbackend.domain.dto.UserDto;
import com.crud.finalbackend.domain.dto.UserRegistrationDto;
import com.crud.finalbackend.service.PreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    private final PreferenceService notificationService;

    public User mapRegistrationDtoToUser(final UserRegistrationDto dto) {
        return User.builder()
                .name( dto.getName() )
                .surname( dto.getSurname() )
                .email( dto.getEmail() )
                .registered( LocalDate.now() )
                .securePassword( dto.getSecurePassword() )
                .preferences( new HashSet<>())
                .build();
    }

    public User mapToUser (final UserDto dto) {
        return User.builder()
                .id( dto.getId() )
                .name( dto.getName() )
                .surname( dto.getSurname() )
                .email( dto.getEmail() )
                .registered( LocalDate.parse(dto.getRegistered()) )
                .securePassword( dto.getSecurePassword() )
                .preferences(
                        dto.getNotificationIds().stream()
                                .map(notificationService::getPreferenceById)
                                .collect(Collectors.toSet())

                )
                .build();
    }

    public UserDto mapToDto(final User user) {
        return UserDto.builder()
                .id( user.getId() )
                .name( user.getName() )
                .surname( user.getSurname() )
                .email( user.getEmail() )
                .securePassword( user.getSecurePassword() )
                .registered( user.getRegistered().toString() )
                .notificationIds(
                        user.getPreferences().stream()
                                .map(Preference::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public List<User> mapToUserList(final List<UserDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(final List<User> dtoList) {
        return dtoList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public List<PreferenceDto> mapToPrefrenceDtoList(final List<Preference> preferenceList, PreferenceMapper preferenceMapper) {
        return preferenceList.stream()
                .map(preferenceMapper::mapToPreferenceDto)
                .collect(Collectors.toList());
    }
}
