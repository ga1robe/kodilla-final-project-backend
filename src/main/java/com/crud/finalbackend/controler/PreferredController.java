package com.crud.finalbackend.controler;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.dto.PreferredDto;
import com.crud.finalbackend.domain.dto.PreferredListDto;
import com.crud.finalbackend.mapper.PreferredMapper;
import com.crud.finalbackend.mapper.UserMapper;
import com.crud.finalbackend.repository.PreferredRepository;
import com.crud.finalbackend.service.PreferredService;
import com.crud.finalbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequestMapping
@AllArgsConstructor
public class PreferredController {
    private final PreferredService preferredService;
    private final UserService userService;
    private final PreferredMapper preferredMapper;
    private final PreferredRepository preferredRepository;
    private final UserMapper userMapper;

    @PostMapping("preferred")
    public void addPreference(@RequestBody PreferredDto dto) {
        preferredService.addPreference( preferredMapper.creationDtoToPreference( dto ) );
    }

    @PutMapping("preferred")
    @Transactional
    public PreferredDto updatePreference(@RequestBody PreferredDto updatingDto) {
        Preference preference = preferredService.getPreferenceById( updatingDto.getId() );

        if(! preference.getTrailBegin().equals( updatingDto.getTrailBegin() )) {preference.setTrailBegin( updatingDto.getTrailBegin() );}
        if(! preference.getTrailEnd().equals( updatingDto.getTrailEnd() )) {preference.setTrailEnd( updatingDto.getTrailEnd() );}

//        if(! preference.getUser().equals( userMapper.mapToUser( updatingDto.getUserDto() ) )) {preference.setUser( userMapper.mapToUser( updatingDto.getUserDto() ) );}
        if(! preference.getUser().equals( updatingDto.getUserId() )) {
            preference.setUser( userService.getUserById( updatingDto.getUserId() )  );
        }

        if(! preference.getDistance().equals( updatingDto.getDistance() ) ) {preference.setDistance( updatingDto.getDistance() );}
        if(! preference.getMinTemperature().equals( updatingDto.getMinTemperature() )) {preference.setMinTemperature( updatingDto.getMinTemperature() );}

        return preferredMapper.mapToPreferenceDto( preference );
    }

    @DeleteMapping("preferred/{id}")
    public void deletePreference(@PathVariable("id") Long id) {
        preferredService.deletePreferenceById(id);
    }

    @GetMapping(value="preferred" , produces = {"application/json"})
    public PreferredListDto getPreferences() {
        return new PreferredListDto( preferredMapper.mapToPrefrenceDtoList( preferredService.getAllPreferences() ) );
    }

    @GetMapping("preferences/")
    public PreferredListDto getPreferencesByDeparturePoint(@RequestParam("point") String point) {
        return new PreferredListDto( preferredMapper.mapToPrefrenceDtoList( preferredService.getAllPreferencesByTrailBegin(point) ) );
    }

//    @RequestMapping(value = "preferred", method = RequestMethod.GET)
//    public ModelAndView preferences() {
//        ModelAndView mav = new ModelAndView("preference/list");
//        mav.addObject("preferences", preferredRepository.findAll());
//        return mav;
//    }

    @GetMapping("preferences/{id}")
    public PreferredDto getPreference(@PathVariable("id") Long id) {
        return preferredMapper.mapToPreferenceDto( preferredService.getPreferenceById(id) );
    }
}
