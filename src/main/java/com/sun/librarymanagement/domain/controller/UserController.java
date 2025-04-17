package com.sun.librarymanagement.domain.controller;

import com.sun.librarymanagement.domain.dto.request.AuthenticationRequestDto;
import com.sun.librarymanagement.domain.dto.request.RegistrationRequestDto;
import com.sun.librarymanagement.domain.dto.response.UserResponseDto;
import com.sun.librarymanagement.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> registration(@RequestBody @Valid RegistrationRequestDto user) {
        userService.registration(user);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> authentication(@RequestBody @Valid AuthenticationRequestDto user) {
        return ResponseEntity.ok(userService.authentication(user));
    }
}
