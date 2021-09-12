package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.User;
import com.crud.finalbackend.except.PreferenceNotFoundException;
import com.crud.finalbackend.repository.PreferredRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PreferredService {
    private final PreferredRepository preferredRepository;
    private final UserService userService;

    /**
     * Adds notification preference to the database and parent object's set of children objects.
     *
     * @param preference
     * @return
     */
    @Transactional
    public Preference addPreference(Preference preference) {
        preferredRepository.save(preference);
        preference.getUser().getPreferences().add(preference);
        return preference;
    }

    public Preference getPreferenceById(Long id) {
        return preferredRepository.findById(id).orElseThrow(PreferenceNotFoundException::new);
    }

    public List<Preference> getAllPreferences() {
        return preferredRepository.findAll();
    }

    /**
     * Returns a list of preferences with begin hiking trail chosen as begin point provided as method @param
     *
     * @param trailBegin
     * @return
     */
    public List<Preference> getAllPreferencesByTrailBegin(String trailBegin) {
        return preferredRepository.findAllByTrailBegin(trailBegin);
    }

    /**
     * Returns a list of preferences with ending hiking trail chosen as ending point provided as method @param
     *
     * @param trailEnd
     * @return
     */
    public List<Preference> getAllPreferencesByTrailEnd(String trailEnd) {
        return preferredRepository.findAllByTrailEnd(trailEnd);
    }

    /**
     * Returns a list of preferences created by user provided as @param If provided user does not exist, returns empty list.
     * Prevents InvalidDataAccessApiUsageException when trying to use unsaved User object
     *
     * @param user
     * @return
     */
    public List<Preference> getAllPreferencesByUser(User user) {
        if( userService.exists(user) ) {
            return preferredRepository.findAllByUser(user);
        }else {
            log.error("Attempting to load preferences of unsaved User: returning empty list");
            return new ArrayList<>();
        }
    }

    @Transactional
    public void deleteAllPreferences() {
        userService.getAllUsers().stream().forEach(User::removeAllPreferences);
    }

    /**
     * Deletes all preferences created by user provided as @param
     *
     * @param user
     */
    @Transactional
    public void deleteAllPreferencesByUser(User user) {
        if( userService.exists(user) ) {
            userService.getUserById( user.getId() ).removeAllPreferences();
        }else {
            log.error("Attempting to delete preferences of unsaved User");
        }
    }

    /**
     * Due to the existing link with parent object, method removes preference with @param id by removing it from
     * the parent object collection.
     * If, by any chance, there is no relation with parent object, preference is removed with repository method.
     *
     * @param id
     */
    @Transactional
    public void deletePreferenceById(Long id) {
        Preference preference = this.getPreferenceById(id);

        if(Objects.nonNull( preference.getUser() )) {
            preference.getUser().removePreference(preference);
        } else {
            preferredRepository.deleteById(id);
        }
    }
}
