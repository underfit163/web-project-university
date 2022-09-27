package com.underfit.trpo.dto;

import com.underfit.trpo.entities.Mark;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link com.underfit.trpo.entities.Mark} entity
 */
@Data
public class MarkDto implements Serializable {
    private Long id;
    private String evaluation;
    private LocalDate passdate;
    private Long studentidfk;
    private Long examidfk;

    public Mark toEntity() {
        Mark mark = new Mark();
        BeanUtils.copyProperties(this, mark);
        return mark;
    }

    public static MarkDto toDto(Mark mark) {
        MarkDto markDto = new MarkDto();
        BeanUtils.copyProperties(mark, markDto);
        markDto.setExamidfk(mark.getExamidfk().getId());
        markDto.setStudentidfk(mark.getStudentidfk().getId());
        return markDto;
    }
}