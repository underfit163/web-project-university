package com.underfit.trpo.dto;

import com.underfit.trpo.entities.Subject;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.underfit.trpo.entities.Subject} entity
 */
@Data
public class SubjectDto implements Serializable {
    private Long id;
    private String subjectname;

    public Subject toEntity() {
        Subject subject = new Subject();
        BeanUtils.copyProperties(this, subject);
        return subject;
    }

    public static SubjectDto toDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        BeanUtils.copyProperties(subject, subjectDto);
        return subjectDto;
    }
}