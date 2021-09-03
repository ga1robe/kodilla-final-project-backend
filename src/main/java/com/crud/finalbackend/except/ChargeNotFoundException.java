package com.crud.finalbackend.except;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Requested payment was not found in database")
public class ChargeNotFoundException extends RuntimeException {
}
