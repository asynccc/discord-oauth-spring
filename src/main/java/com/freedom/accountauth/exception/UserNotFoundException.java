package com.freedom.accountauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Couldn't be possible to find a user with the given id")
public class UserNotFoundException extends Exception {
}
