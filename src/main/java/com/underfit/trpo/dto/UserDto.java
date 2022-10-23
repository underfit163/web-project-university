package com.underfit.trpo.dto;

import com.underfit.trpo.entities.Role;
import com.underfit.trpo.entities.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.underfit.trpo.entities.User} entity
 */
@Data
public class UserDto implements Serializable {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private String email;

    public User toEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}