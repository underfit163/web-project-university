package com.underfit.trpo.servicetest;

import com.underfit.trpo.dao.MarkRepository;
import com.underfit.trpo.dto.MarkDto;
import com.underfit.trpo.entities.Exam;
import com.underfit.trpo.entities.Mark;
import com.underfit.trpo.service.MarkServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class MarkServiceImplTest {
    @MockBean
    private MarkRepository markRepository;

    @Autowired
    private MarkServiceImpl markService;

    private Mark mark;

    @BeforeEach
    public void setup() {
        mark = new Mark(1L, "4", LocalDate.of(2019, 1, 14), null, null);
    }

    @Test
    public void getByIdTest() {
        when(markRepository.findById(1L)).thenReturn(Optional.of(mark));
        MarkDto examDto = markService.getById(mark.getId());
        Assertions.assertEquals(mark.getId(), examDto.toEntity().getId());
    }

    @Test
    public void getAllWhenNullTest() {
        when(markRepository.findAllMarks()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> markService.getAll());
    }

    @Test
    public void getAllTest() {
        Mark mark1 = new Mark(1L, "4", LocalDate.of(2019, 1, 14), null, null);
        List<Mark> list = List.of(mark, mark1);
        when(markRepository.findAllMarks()).thenReturn(Optional.of(list));
        List<MarkDto> examsDto = markService.getAll();
        assertThat(examsDto).hasSize(2);
    }

    @Test
    public void getAllByIdTest() {
        Mark mark1 = new Mark(1L, "4", LocalDate.of(2019, 1, 14), null, new Exam(1L, "Зачет", 24, 2,null, null));
        List<Mark> list = List.of(mark1);
        when(markRepository.findAllMarksByIdExam(1L)).thenReturn(Optional.of(list));
        List<MarkDto> examsDto = markService.getAllByIdExam(1L);
        assertThat(examsDto).hasSize(1);
        Assertions.assertEquals("4", examsDto.get(0).getEvaluation());
    }

    @Test
    public void saveTest() {
        when(markRepository.save(any(Mark.class))).thenReturn(mark);
        MarkDto markDto = MarkDto.toDto(mark);
        MarkDto saved = markService.save(markDto);
        Assertions.assertEquals(mark.getId(), saved.getId());
    }

    @Test
    public void deleteTest() {
        doNothing().when(markRepository).deleteById(1L);
        markService.delete(mark.getId());
        Assertions.assertFalse(markRepository.findById(mark.getId()).isPresent());
    }
}
