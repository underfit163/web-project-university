package com.underfit.trpo.dao;

import com.underfit.trpo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLoginContainsIgnoreCase(String login);
}
