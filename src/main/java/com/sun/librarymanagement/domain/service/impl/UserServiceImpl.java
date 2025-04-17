package com.sun.librarymanagement.domain.service.impl;

import com.sun.librarymanagement.domain.dto.request.AuthenticationRequestDto;
import com.sun.librarymanagement.domain.dto.request.RegistrationRequestDto;
import com.sun.librarymanagement.domain.dto.response.UserResponseDto;
import com.sun.librarymanagement.domain.entity.UserEntity;
import com.sun.librarymanagement.domain.repository.UserRepository;
import com.sun.librarymanagement.domain.service.UserService;
import com.sun.librarymanagement.exception.AppError;
import com.sun.librarymanagement.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registration(RegistrationRequestDto user) {
        userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
            .stream()
            .findAny()
            .ifPresent(entity -> {
                throw new AppException(AppError.ACCOUNT_ALREADY_EXISTS);
            });
        UserEntity userEntity = UserEntity.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .build();
        userRepository.save(userEntity);
    }

    @Override
    public UserResponseDto authentication(AuthenticationRequestDto user) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail())
            .filter(entity -> passwordEncoder.matches(user.getPassword(), entity.getPassword()))
            .orElseThrow(() -> new AppException(AppError.LOGIN_INFO_INVALID));
        return convertEntityToDto(userEntity);
    }

    private UserResponseDto convertEntityToDto(UserEntity entity) {
        return UserResponseDto.builder()
            .token(jwtUtils.encode(entity.getEmail()))
            .username(entity.getUsername())
            .email(entity.getEmail())
            .build();
    }
}
