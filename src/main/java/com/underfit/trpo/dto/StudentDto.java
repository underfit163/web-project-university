package com.underfit.trpo.dto;

import com.underfit.trpo.entities.Student;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.underfit.trpo.entities.Student} entity
 */
@Data
public class StudentDto implements Serializable {
    private Long id;
    private String fio;
    private LocalDate birthday;
    private String gender;
    private String phone;
    private String email;

    public Student toEntity() {
        Student student = new Student();
        BeanUtils.copyProperties(this, student);
        return student;
    }

    public static StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }
}