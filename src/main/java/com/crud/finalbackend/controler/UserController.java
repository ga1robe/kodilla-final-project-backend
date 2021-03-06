package com.crud.finalbackend.controler;

import com.crud.finalbackend.domain.User;
import com.crud.finalbackend.domain.dto.UserDto;
import com.crud.finalbackend.domain.dto.UserListDto;
import com.crud.finalbackend.domain.dto.UserRegisterDto;
import com.crud.finalbackend.mapper.UserMapper;
import com.crud.finalbackend.repository.UserRepository;
import com.crud.finalbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @PostMapping("users")
    public void registerUser(@RequestBody UserRegisterDto dto) {
        userService.addUser( userMapper.mapRegistrationDtoToUser(dto) );
    }

    @GetMapping("users/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userMapper.mapToDto( userService.getUserById(id) );
    }

    @GetMapping("users")
    public UserListDto getUsers() {
        return new UserListDto( userMapper.mapToUserDtoList( userService.getAllUsers() ) ) ;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView preferences() {
        ModelAndView mav = new ModelAndView("user/list");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }

    @PutMapping("users")
    @Transactional
    public UserDto updateUser(@RequestBody UserDto updatingDto) {
        User user = userService.getUserById( updatingDto.getId() );

        if(! user.getName().equals( updatingDto.getName() )) { user.setName( updatingDto.getName() ); }
        if(! user.getSurname().equals( updatingDto.getSurname() )) { user.setSurname( updatingDto.getSurname() ); }
        if(! user.getEmail().equals( updatingDto.getEmail() )) { user.setEmail( updatingDto.getEmail() ); }
        if(! user.getSecurePassword().equals( updatingDto.getSecurePassword() )) { user.setSecurePassword( updatingDto.getSecurePassword() ); }

        return userMapper.mapToDto(user);
    }

    @DeleteMapping("users/{id}")
    @Transactional
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }
}
