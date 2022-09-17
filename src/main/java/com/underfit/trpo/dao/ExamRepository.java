package com.underfit.trpo.dao;

import com.underfit.trpo.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}