package com.underfit.trpo.dao;

import com.underfit.trpo.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> {
}