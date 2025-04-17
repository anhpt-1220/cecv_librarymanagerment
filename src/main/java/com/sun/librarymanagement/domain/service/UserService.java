package com.sun.librarymanagement.domain.service;

import com.sun.librarymanagement.domain.dto.request.AuthenticationRequestDto;
import com.sun.librarymanagement.domain.dto.request.RegistrationRequestDto;
import com.sun.librarymanagement.domain.dto.response.UserResponseDto;

public interface UserService {

    void registration(final RegistrationRequestDto user);

    UserResponseDto authentication(final AuthenticationRequestDto user);
}
