package com.sun.librarymanagement.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AppError {
    ACCOUNT_ALREADY_EXISTS("Account already exists.", HttpStatus.CONFLICT),
    ;

    private final String message;

    private final HttpStatus status;
}
