package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.NotificationPreference;
import com.crud.finalbackend.domain.User;
import com.crud.finalbackend.except.NotificationPreferenceNotFoundException;
import com.crud.finalbackend.repository.NotificationPreferenceRepository;
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
public class NotificationPreferenceService {
    private final NotificationPreferenceRepository notificationPreferenceRepository;
    private final UserService userService;

    /**
     * Adds notification preference to the database and parent object's set of children objects.
     *
     * @param preference
     * @return
     */
    @Transactional
    public NotificationPreference addPreference(NotificationPreference preference) {
        notificationPreferenceRepository.save(preference);
        preference.getUser().getNotificationPreferences().add(preference);
        return preference;
    }

    public NotificationPreference getPreferenceById(Long id) {
        return notificationPreferenceRepository.findById(id).orElseThrow(NotificationPreferenceNotFoundException::new);
    }

    public List<NotificationPreference> getAllPreferences() {
        return notificationPreferenceRepository.findAll();
    }

    /**
     * Returns a list of preferences with begin hiking trail chosen as begin point provided as method @param
     *
     * @param trailBegin
     * @return
     */
    public List<NotificationPreference> getAllPreferencesByTrailBegin(String trailBegin) {
        return notificationPreferenceRepository.findAllByTrailBegin(trailBegin);
    }

    /**
     * Returns a list of preferences with ending hiking trail chosen as ending point provided as method @param
     *
     * @param trailEnd
     * @return
     */
    public List<NotificationPreference> getAllPreferencesByTrailEnd(String trailEnd) {
        return notificationPreferenceRepository.findAllByTrailEnd(trailEnd);
    }

    /**
     * Returns a list of preferences created by user provided as @param If provided user does not exist, returns empty list.
     * Prevents InvalidDataAccessApiUsageException when trying to use unsaved User object
     *
     * @param user
     * @return
     */
    public List<NotificationPreference> getAllPreferencesByUser(User user) {
        if( userService.exists(user) ) {
            return notificationPreferenceRepository.findAllByUser(user);
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
        NotificationPreference preference = this.getPreferenceById(id);

        if(Objects.nonNull( preference.getUser() )) {
            preference.getUser().removePreference(preference);
        } else {
            notificationPreferenceRepository.deleteById(id);
        }
    }
}
