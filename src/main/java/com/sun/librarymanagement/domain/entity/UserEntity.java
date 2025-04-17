package com.sun.librarymanagement.domain.entity;

import com.sun.librarymanagement.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "is_active", nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean isActive;

    @Column(name = "is_verified", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isVerified;

    @Column(name = "verify_token")
    private String verifyToken;

    @Builder
    public UserEntity(
        Long id,
        String username,
        String email,
        String password
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
