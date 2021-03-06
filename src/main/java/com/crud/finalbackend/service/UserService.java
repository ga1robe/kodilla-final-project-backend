package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.ServiceUsageRecord;
import com.crud.finalbackend.domain.dto.UserDto;
import com.crud.finalbackend.except.UserEmailAlreadyExistsException;
import com.crud.finalbackend.except.UserNotFoundException;
import com.crud.finalbackend.domain.User;
import com.crud.finalbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Method checks if in database already exists user with email provided as @param
     * Email is unique in database, so can not exist two users with same email address
     *
     * @param email
     * @return true or false
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Wraps existsByEmail(String email) so that @param object can be user
     *
     * @param user which existence in database is checked by its id
     * @return
     */
    public boolean exists(User user) {
        return existsByEmail( user.getEmail() );
    }

    /**
     * Adds new user to the database. Before checks if it already exists.
     *
     * @param user
     * @return
     */
    @Transactional
    public User addUser(final User user) {
        if( this.exists(user) ) {
            throw new UserEmailAlreadyExistsException();
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Transactional
    public void deleteUserById(final Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}