package com.underfit.trpo.dao;

import com.underfit.trpo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}