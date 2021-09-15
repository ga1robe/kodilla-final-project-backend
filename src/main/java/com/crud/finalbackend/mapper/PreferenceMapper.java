package com.crud.finalbackend.mapper;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.dto.PreferenceDto;
import com.crud.finalbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PreferenceMapper {
    private final UserMapper userMapper;
    private final UserService userService;

    public Preference creationDtoToPreference(final PreferenceDto dto) {
        return Preference.builder()
                .id( dto.getId() )
                .trailBegin( dto.getTrailBegin() )
                .trailEnd( dto.getTrailEnd() )
                .distance( dto.getDistance() )
                .minTemperature( dto.getMinTemperature() )
                .user( userService.getUserById( dto.getId() ) )
                .build();

    }

    public Preference mapToPreference(final PreferenceDto dto) {
        return Preference.builder()
                .id( dto.getId() )
                .trailBegin( dto.getTrailBegin() )
                .trailEnd( dto.getTrailEnd() )
                .distance( dto.getDistance() )
                .minTemperature( dto.getMinTemperature() )
                .user(  userService.getUserById( dto.getUserId() ) )
                .build();
    }

    public List<Preference> mapToPreferenceList (final List<PreferenceDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToPreference)
                .collect(Collectors.toList());
    }

    public PreferenceDto mapToPreferenceDto(final Preference preference) {
        return PreferenceDto.builder()
                .id( preference.getId() )
                .trailBegin( preference.getTrailBegin() )
                .trailEnd( preference.getTrailEnd() )
                .distance( preference.getDistance() )
                .minTemperature( preference.getMinTemperature() )
                .userId(preference.getUser().getId() )
                .build();
    }

    public List<PreferenceDto> mapToPrefrenceDtoList(final List<Preference> preferenceList) {
        return preferenceList.stream()
                .map(this::mapToPreferenceDto)
                .collect(Collectors.toList());
    }
}
