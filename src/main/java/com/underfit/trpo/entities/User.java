package com.underfit.trpo.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users", indexes = {
        @Index(name = "users_login_key", columnList = "login", unique = true),
        @Index(name = "users_email_key", columnList = "email", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role")
    private Role role;

    @Column(name = "email", length = 100)
    private String email;

    public User(String login, String password, Role role, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }
}