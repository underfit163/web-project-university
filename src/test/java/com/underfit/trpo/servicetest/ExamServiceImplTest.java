package com.underfit.trpo.servicetest;

import com.underfit.trpo.dao.ExamRepository;
import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.entities.Exam;
import com.underfit.trpo.service.ExamServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamServiceImplTest {
    @MockBean
    private ExamRepository examRepository;

    @Autowired
    private ExamServiceImpl examService;

    private Exam exam;

    @BeforeEach
    public void setup() {
        exam = new Exam(1L, "экзамен", 54, 2, null, null);
    }

    @Test
    public void getByIdTest() {
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        ExamDto examDto = examService.getById(exam.getId());
        Assertions.assertEquals(exam.getId(), examDto.toEntity().getId());
    }

    @Test
    public void getAllWhenNullTest() {
        when(examRepository.findAllExams()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> examService.getAll());
    }

    @Test
    public void getAllTest() {
        Exam exam1 = new Exam(2L, "зачет", 36, 1, null, null);
        List<Exam> list = List.of(exam, exam1);
        when(examRepository.findAllExams()).thenReturn(Optional.of(list));
        List<ExamDto> examsDto = examService.getAll();
        assertThat(examsDto).hasSize(2);
    }

    @Test
    public void saveTest() {
        when(examRepository.save(any(Exam.class))).thenReturn(exam);
        ExamDto examDto = ExamDto.toDto(exam);
        ExamDto saved = examService.save(examDto);
        Assertions.assertEquals(exam.getId(), saved.getId());
    }

    @Test
    public void deleteTest() {
        doNothing().when(examRepository).deleteById(1L);
        examService.delete(exam.getId());
        Assertions.assertFalse(examRepository.findById(exam.getId()).isPresent());
    }
}
