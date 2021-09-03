package com.crud.finalbackend.except;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="User with given email already exists")
public class UserEmailAlreadyExistsException extends RuntimeException {
}