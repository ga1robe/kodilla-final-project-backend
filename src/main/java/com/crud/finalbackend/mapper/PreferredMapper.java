package com.crud.finalbackend.mapper;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.dto.PreferredDto;
import com.crud.finalbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PreferredMapper {
    private final UserMapper userMapper;
    private final UserService userService;

    public Preference creationDtoToPreference(final PreferredDto dto) {
        return Preference.builder()
                .id( dto.getId() )
                .trailBegin( dto.getTrailBegin() )
                .trailEnd( dto.getTrailEnd() )
                .distance( dto.getDistance() )
                .minTemperature( dto.getMinTemperature() )
//                .user( userService.getUserById( dto.getUserDto().getId() ) )
                .user( userService.getUserById( dto.getId() ) )
                .build();

    }

    public Preference mapToPreference(final PreferredDto dto) {
        return Preference.builder()
                .id( dto.getId() )
                .trailBegin( dto.getTrailBegin() )
                .trailEnd( dto.getTrailEnd() )
                .distance( dto.getDistance() )
                .minTemperature( dto.getMinTemperature() )
//                .user( userMapper.mapToUser( dto.getUserDto() ) )
                .user(  userService.getUserById( dto.getUserId() ) )
                .build();
    }

    public List<Preference> mapToPreferenceList (final List<PreferredDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToPreference)
                .collect(Collectors.toList());
    }

    public PreferredDto mapToPreferenceDto(final Preference preference) {
        return PreferredDto.builder()
                .id( preference.getId() )
                .trailBegin( preference.getTrailBegin() )
                .trailEnd( preference.getTrailEnd() )
                .distance( preference.getDistance() )
                .minTemperature( preference.getMinTemperature() )
                .userId(preference.getUser().getId() )
                .build();
    }

    public List<PreferredDto> mapToPrefrenceDtoList(final List<Preference> preferenceList) {
        return preferenceList.stream()
                .map(this::mapToPreferenceDto)
                .collect(Collectors.toList());
    }
}
