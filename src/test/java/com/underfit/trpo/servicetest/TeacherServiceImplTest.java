package com.underfit.trpo.servicetest;

import com.underfit.trpo.dao.TeacherRepository;
import com.underfit.trpo.dto.TeacherDto;
import com.underfit.trpo.entities.Teacher;
import com.underfit.trpo.service.TeacherServiceImpl;
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
public class TeacherServiceImplTest {
    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherServiceImpl teacherService;

    private Teacher teacher;

    @BeforeEach
    public void setup() {
        teacher = new Teacher(1L,
                "Алексей", LocalDate.of(1978, 3, 20), "м", "доцент",
                "кандидат наук", "111111");
    }

    @Test
    public void getByIdTest() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        TeacherDto teacherDto = teacherService.getById(teacher.getId());
        Assertions.assertEquals(teacher.getId(), teacherDto.toEntity().getId());
    }

    @Test
    public void getAllWhenNullTest() {
        when(teacherRepository.findAll()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> teacherService.getAll());
    }

    @Test
    public void getAllTest() {
        Teacher teacher1 = new Teacher(1L,
                "Петр", LocalDate.of(1960, 1, 20), "м", "доцент",
                "кандидат наук", "111112");
        List<Teacher> list = List.of(teacher, teacher1);
        when(teacherRepository.findAll()).thenReturn(list);
        List<TeacherDto> teachersDto = teacherService.getAll();
        assertThat(teachersDto).hasSize(2);
    }

    @Test
    public void saveTest() {
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);
        TeacherDto studentDto = TeacherDto.toDto(teacher);
        TeacherDto saved = teacherService.save(studentDto);
        Assertions.assertEquals(teacher.getId(), saved.getId());
    }

    @Test
    public void deleteTest() {
        doNothing().when(teacherRepository).deleteById(1L);
        teacherService.delete(teacher.getId());
        Assertions.assertFalse(teacherRepository.findById(teacher.getId()).isPresent());
    }
}
