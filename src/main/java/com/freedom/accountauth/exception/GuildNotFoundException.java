package com.freedom.accountauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Guild not found")
public class GuildNotFoundException extends Exception {
}
