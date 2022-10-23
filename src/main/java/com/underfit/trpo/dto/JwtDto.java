package com.underfit.trpo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.underfit.trpo.entities.User} entity
 */
@Data
@AllArgsConstructor
public class JwtDto implements Serializable {
    private String jwt;
    private Long id;
    private String login;
    private String email;
    private String role;
}