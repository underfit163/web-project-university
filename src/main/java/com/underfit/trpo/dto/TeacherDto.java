package com.underfit.trpo.dto;

import com.underfit.trpo.entities.Teacher;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.underfit.trpo.entities.Teacher} entity
 */
@Data
public class TeacherDto implements Serializable {
    private Long id;
    private String fio;
    private LocalDate birthday;
    private String gender;
    private String title;
    private String teacherdegree;
    private String phone;

    public Teacher toEntity() {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(this, teacher);
        return teacher;
    }

    public static TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        BeanUtils.copyProperties(teacher, teacherDto);
        return teacherDto;
    }
}