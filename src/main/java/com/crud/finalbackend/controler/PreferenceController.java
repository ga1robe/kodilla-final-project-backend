package com.crud.finalbackend.controler;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.dto.PreferenceDto;
import com.crud.finalbackend.domain.dto.PreferenceListDto;
import com.crud.finalbackend.mapper.PreferenceMapper;
import com.crud.finalbackend.service.PreferenceService;
import com.crud.finalbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class PreferenceController {
    private final PreferenceService preferenceService;
    private final UserService userService;
    private final PreferenceMapper preferenceMapper;

    @PostMapping("preferences")
    public void addPreference(@RequestBody PreferenceDto dto) {
        preferenceService.addPreference( preferenceMapper.creationDtoToPreference( dto ) );
    }

    @PutMapping("preferences")
    @Transactional
    public PreferenceDto updatePreference(@RequestBody PreferenceDto updatingDto) {
        Preference preference = preferenceService.getPreferenceById( updatingDto.getId() );

        if(! preference.getTrailBegin().equals( updatingDto.getTrailBegin() )) {preference.setTrailBegin( updatingDto.getTrailBegin() );}
        if(! preference.getTrailEnd().equals( updatingDto.getTrailEnd() )) {preference.setTrailEnd( updatingDto.getTrailEnd() );}

        if(! preference.getUser().equals( updatingDto.getUserId() )) {
            preference.setUser( userService.getUserById( updatingDto.getUserId() )  );
        }

        if(! preference.getDistance().equals( updatingDto.getDistance() ) ) {preference.setDistance( updatingDto.getDistance() );}
        if(! preference.getMinTemperature().equals( updatingDto.getMinTemperature() )) {preference.setMinTemperature( updatingDto.getMinTemperature() );}

        return preferenceMapper.mapToPreferenceDto( preference );
    }

    @DeleteMapping("preferences/{id}")
    public void deletePreference(@PathVariable("id") Long id) {
        preferenceService.deletePreferenceById(id);
    }

    @GetMapping(value="preferences" , produces = {"application/json"})
    public PreferenceListDto getPreferences() {
        return new PreferenceListDto( preferenceMapper.mapToPrefrenceDtoList( preferenceService.getAllPreferences() ) );
    }

    @GetMapping("preferences/")
    public PreferenceListDto getPreferencesByDeparturePoint(@RequestParam("point") String point) {
        return new PreferenceListDto( preferenceMapper.mapToPrefrenceDtoList( preferenceService.getAllPreferencesByTrailBegin(point) ) );
    }

    @GetMapping("preferences/{id}")
    public PreferenceDto getPreference(@PathVariable("id") Long id) {
        return preferenceMapper.mapToPreferenceDto( preferenceService.getPreferenceById(id) );
    }
}
