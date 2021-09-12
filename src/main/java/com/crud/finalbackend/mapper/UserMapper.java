package com.crud.finalbackend.mapper;


import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.User;
import com.crud.finalbackend.domain.dto.PreferredDto;
import com.crud.finalbackend.domain.dto.UserDto;
import com.crud.finalbackend.domain.dto.UserRegistrationDto;
import com.crud.finalbackend.service.PreferredService;
import com.crud.finalbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    private final PreferredService preferredService;
    private final UserService userService;

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
                        dto.getPreferenceIds().stream()
                                .map(preferredService::getPreferenceById)
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
                .preferenceIds(
                        user.getPreferences().stream()
                                .map(Preference::getId)
                                .collect(Collectors.toSet())
                )
                .build();
    }

//    public List<User> mapToUserList(final List<UserDto> dtoList) {
//        return dtoList.stream()
////                .map(this::mapToUser)
//                .map(this::mapToUserList);
////                .collect(Collectors.toList());
//    }

    public List<UserDto> mapToUserDtoList(final List<User> dtoList) {
        return dtoList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public List<PreferredDto> mapToPrefrenceDtoList(final List<Preference> preferenceList, PreferredMapper preferredMapper) {
        return preferenceList.stream()
                .map(preferredMapper::mapToPreferenceDto)
                .collect(Collectors.toList());
    }
}
