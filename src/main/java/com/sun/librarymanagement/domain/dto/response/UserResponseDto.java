package com.sun.librarymanagement.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserResponseDto {

    private String token;

    private String username;

    private String email;
}
