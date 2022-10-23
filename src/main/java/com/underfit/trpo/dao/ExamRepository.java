package com.underfit.trpo.dao;

import com.underfit.trpo.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("FROM Exam e JOIN FETCH e.subjectidfk JOIN FETCH e.teacheridfk")
    Optional<List<Exam>> findAllExams();
}