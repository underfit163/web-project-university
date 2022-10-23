package com.underfit.trpo.dto;

import com.underfit.trpo.entities.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.underfit.trpo.entities.Exam} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto implements Serializable {
    private Long id;
    private String passtype;
    private Integer totalhours;
    private Integer semester;
    private Long subjectidfk;
    private Long teacheridfk;

    public Exam toEntity() {
        Exam exam = new Exam();
        BeanUtils.copyProperties(this, exam);
        return exam;
    }

    public static ExamDto toDto(Exam exam) {
        ExamDto examDto = new ExamDto();
        BeanUtils.copyProperties(exam, examDto);
        examDto.setSubjectidfk(exam.getSubjectidfk().getId());
        examDto.setTeacheridfk(exam.getTeacheridfk().getId());
        return examDto;
    }
}