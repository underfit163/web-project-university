package com.underfit.trpo.controller;

import com.underfit.trpo.dao.UserRepository;
import com.underfit.trpo.dto.JwtDto;
import com.underfit.trpo.dto.LoginDto;
import com.underfit.trpo.dto.UserDto;
import com.underfit.trpo.entities.Role;
import com.underfit.trpo.security.JwtUtils;
import com.underfit.trpo.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;//занимается аутентификацией пользователя
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);//Что это такое? контекст для секюрити(контейнер куда помещаем данные)
        //в principal(UserDetailsImpl) хранится информация о нашем пользователе
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .findFirst().orElse(null);
        return ResponseEntity.ok(new JwtDto(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already exist!");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already exist!");
        }
        if(userDto.getRole() == null) {
            userDto.setRole(Role.ROLE_USER);
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(userDto.toEntity());
        return ResponseEntity.ok("User registered successfully!");
    }
}
