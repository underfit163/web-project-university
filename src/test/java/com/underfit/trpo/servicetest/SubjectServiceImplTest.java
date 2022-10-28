package com.underfit.trpo.servicetest;

import com.underfit.trpo.dao.SubjectRepository;
import com.underfit.trpo.dto.SubjectDto;
import com.underfit.trpo.entities.Subject;
import com.underfit.trpo.service.SubjectServiceImpl;
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
public class SubjectServiceImplTest {
    @MockBean
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectServiceImpl subjectService;

    private Subject subject;

    @BeforeEach
    public void setup() {
        subject = new Subject(1L, "noSql");
    }

    @Test
    public void getByIdTest() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        SubjectDto studentDto = subjectService.getById(subject.getId());
        Assertions.assertEquals(subject.getId(), studentDto.toEntity().getId());
    }

    @Test
    public void getAllWhenNullTest() {
        when(subjectRepository.findAll()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> subjectService.getAll());
    }

    @Test
    public void getAllTest() {
        Subject subject11 = new Subject(1L, "Java");
        List<Subject> list = List.of(subject, subject11);
        when(subjectRepository.findAll()).thenReturn(list);
        List<SubjectDto> subjectsDto = subjectService.getAll();
        assertThat(subjectsDto).hasSize(2);
    }

    @Test
    public void saveTest() {
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        SubjectDto subjectDto = SubjectDto.toDto(subject);
        SubjectDto saved = subjectService.save(subjectDto);
        Assertions.assertEquals(subject.getId(), saved.getId());
    }

    @Test
    public void deleteTest() {
        doNothing().when(subjectRepository).deleteById(1L);
        subjectService.delete(subject.getId());
        Assertions.assertFalse(subjectRepository.findById(subject.getId()).isPresent());
    }
}
