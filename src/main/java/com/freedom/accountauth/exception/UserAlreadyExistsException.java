package com.freedom.accountauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Provided user id already exists in your database")
public class UserAlreadyExistsException extends Exception {
}
