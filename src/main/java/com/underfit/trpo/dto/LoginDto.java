package com.underfit.trpo.dto;

import com.underfit.trpo.entities.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.underfit.trpo.entities.User} entity
 */
@Data
public class LoginDto implements Serializable {
    private String login;
    private String password;


    public static LoginDto toDto(User user) {
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(user, loginDto);
        return loginDto;
    }
}