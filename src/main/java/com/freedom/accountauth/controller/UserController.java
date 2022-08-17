package com.freedom.accountauth.controller;

import com.freedom.accountauth.dto.request.CreateUserRequest;
import com.freedom.accountauth.dto.response.user.ReadUserResponse;
import com.freedom.accountauth.exception.UserAlreadyExistsException;
import com.freedom.accountauth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadUserResponse create(@NonNull CreateUserRequest request) throws UserAlreadyExistsException {
        return service.create(request);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadUserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReadUserResponse findById(@PathVariable long id) {
        return service.findById(id);
    }

}
