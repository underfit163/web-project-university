package com.underfit.trpo.dao;

import com.underfit.trpo.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query("FROM Mark m JOIN FETCH m.studentidfk JOIN FETCH m.examidfk")
    Optional<List<Mark>> findAllMarks();
}